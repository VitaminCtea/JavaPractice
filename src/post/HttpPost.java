package post;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class HttpPost {
    private static final Map<Character, String> whiteSpaceCharacterMap = Map.of(
            '\b', "\\b", '\f', "\\f", '\n', "\\n", '\r',
            "\\r", '\t', "\\t", '"', "\\\"", '\\', "\\\\"
    );

    private static final HttpClient.Builder httpRequest = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS);

    public static void post(String fullFileName, boolean isAsync) {
        Path propsPath = Paths.get(System.getProperty("user.dir"), fullFileName);
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propsPath)) { props.load(in); } catch (IOException e) { e.printStackTrace(); }
        String urlString = "" + props.remove("url");
        String contentType = "" + props.remove("Content-Type");

        if (contentType.equals("multipart/form-data")) {
            contentType += ";boundary=" + new BigInteger(256, new Random());
            props.replaceAll((k, v) -> v.toString().startsWith("file://") ? propsPath.getParent().resolve(Paths.get(v.toString().substring(7))) : v);
        }

        try {
            HttpPost.class
                .getDeclaredMethod(isAsync ? "doAsyncPost" : "doPost", String.class, String.class, Map.class)
                .invoke(null, urlString, contentType, props);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) { e.printStackTrace(); }
    }

    private static void doPost(String url, String contentType, Map<Object, Object> props) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = httpRequest.build();
        createFile(
                "post.html",
                client.send(HttpPost.baseDoSyncPost(url, contentType, props), HttpResponse.BodyHandlers.ofString()).body()
        );
    }

    private static void doAsyncPost(String url, String contentType, Map<Object, Object> props) throws IOException, URISyntaxException {
        HttpClient httpClient = httpRequest.executor(Executors.newCachedThreadPool()).build();
        httpClient.sendAsync(HttpPost.baseDoSyncPost(url, contentType, props), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(result -> createFile("postAsync.html", result));
    }

    private static HttpRequest baseDoSyncPost(String url, String contentType, Map<Object, Object> props) throws IOException, URISyntaxException {
        HttpRequest.BodyPublisher publisher;
        if (contentType.startsWith("multipart/form-data"))
            publisher = HttpPost.ofMimeMultipartData(props, contentType.substring(contentType.lastIndexOf("=") + 1));
        else if (contentType.equals("application/x-www-form-urlencoded"))
            publisher = HttpPost.ofFormData(props);
        else {
            contentType = "application/json";
            publisher = HttpPost.ofSimpleJSON(props);
        }
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(new URI(url)).header("Content-Type", contentType);
        return builder.POST(publisher).build();
    }

    private static byte[] bytes(String s) { return s.getBytes(StandardCharsets.UTF_8); }

    public static HttpRequest.BodyPublisher ofMimeMultipartData(Map<Object, Object> props, String boundary) throws IOException {
        /*
          multipart/form-data格式

          --111499225464205653449959312685927417995487581146539367420830618545691914917161 -> 分隔符
          Content-Disposition: form-data; name=
          "MAX_FILE_SIZE" -> 参数名称

          1048576 -> 值

          --111499225464205653449959312685927417995487581146539367420830618545691914917161
          Content-Disposition: form-data; name=
          "file"; filename="cup.png" -> 文件的名字
          Content-Type: image/png -> 文件的MIME类型

          ...

          结束符为：--111499225464205653449959312685927417995487581146539367420830618545691914917161--
         */
        List<byte[]> byteArrays = new ArrayList<>();
        byte[] separator = bytes("--" + boundary + "\nContent-Disposition: form-data; name=");
        props.forEach((key, value) -> {
            byteArrays.add(separator);
            if (value instanceof Path) {
                Path path = (Path) value;
                try {
                    byteArrays.add(
                            bytes(
                                    "\"" + key + "\"; " + "filename=\"" + path.getFileName() + "\"\n"
                                    + "Content-Type: " + Files.probeContentType(path) + "\n"
                            )
                    );
                    byteArrays.add(Files.readAllBytes(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else byteArrays.add(bytes("\"" + key + "\"\n\n" + value + "\n"));
        });

        byteArrays.add(bytes("--" + boundary + "--"));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    private static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> props) {
        UnaryOperator<String> encode = val -> URLEncoder.encode(val, StandardCharsets.UTF_8);
        return HttpRequest.BodyPublishers.ofString(
                normalizedParameters(
                        null,
                        null,
                        "&",
                        props,
                        (key, value) -> encode.apply(key.toString()) + "=" + encode.apply(value.toString())
                )
        );
    }

    private static HttpRequest.BodyPublisher ofSimpleJSON(Map<Object, Object> props) {
        return HttpRequest.BodyPublishers.ofString(
                normalizedParameters(
                        "{",
                        "}",
                        ", ",
                        props,
                        (key, value) -> jsonEscape(key.toString()) + ": " + jsonEscape(value.toString())
                )
        );
    }

    private static <T extends String> String normalizedParameters(
            T prefix,
            T suffix,
            T separator,
            Map<Object, Object> props,
            BiFunction<Object, Object, T> getValue
    ) {
        List<String> params = new ArrayList<>();
        Predicate<T> hasSymbol = symbol -> symbol != null && symbol.length() > 0;
        if (hasSymbol.test(prefix)) params.add(prefix);
        props.forEach((key, value) -> params.add(getValue.apply(key, value)));
        if (hasSymbol.test(suffix)) params.add(suffix);
        return String.join(separator, params);
    }

    private static StringBuilder jsonEscape(String str) {
        StringBuilder stringBuilder = new StringBuilder("\"");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String whiteSpace = whiteSpaceCharacterMap.get(ch);
            stringBuilder.append(whiteSpace == null ? ch : whiteSpace);
        }
        stringBuilder.append("\"");
        return stringBuilder;
    }

    private static void createFile(String fullFileName, String result) {
        Path path = Paths.get(fullFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.newOutputStream(path).write(result.getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }
}