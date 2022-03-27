package post;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

public class Post {
    public static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        var connection = (HttpURLConnection) url.openConnection();

        if (userAgent != null) connection.setRequestProperty("User-Agent", userAgent);
        if (redirects >= 0) connection.setInstanceFollowRedirects(false);   // 人工实现重定向，在连接到服务器之前，将关闭自动重定向

        connection.setDoOutput(true);

        try (var out = new PrintWriter(connection.getOutputStream())) {
            var first = true;
            for (Map.Entry<Object, Object> pair: nameValuePairs.entrySet()) {
                if (first) first = false;
                else out.print('&');
                out.print(pair.getKey().toString());
                out.print('=');
                out.print(URLEncoder.encode(pair.getValue().toString(), StandardCharsets.UTF_8));
            }
        }

        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";

        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER
            ) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects + 1);
                }
            }
        } else if (redirects == 0) throw new IOException("Too many redirects");

        var response = new StringBuilder();
        try (var in = new Scanner(connection.getInputStream(), encoding)) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException e) {
            InputStream err = connection.getErrorStream();
            if (err == null) throw e;
            try (var in = new Scanner(err)) {
                response.append(in.nextLine());
                response.append("\n");
            }
        }
        return response.toString();
    }
}