package i18N;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaiduTranslate {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20220510001210305";
    private static final String SECURITY_KEY = "9llZe6jz_XjukQp4itYG";

    private static class I18NConfig {
        public ArrayList<String> keys;
        public StringBuilder searchQuery;
        public I18NConfig(int size) {
            this.keys = new ArrayList<>(size);
            this.searchQuery = new StringBuilder();
        }
    }

    public static void main(String[] args) {
        createI18NLanguageProperties(
                "language",
                new TransApi.LanguageVariousCountries[] {
                        TransApi.LanguageVariousCountries.ZH,
                        TransApi.LanguageVariousCountries.EN,
                        TransApi.LanguageVariousCountries.JP,
                        TransApi.LanguageVariousCountries.KOR
                }
        );
    }

    public static <T extends TransApi.LanguageVariousCountries> void createI18NLanguageProperties(String basePropertiesFilename, T[] locales) {
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            Path basePath = Paths.get(System.getProperty("user.dir"), "resources");
            I18NConfig i18NConfig = collectConfigKeyValue(getProperties(basePath, basePropertiesFilename), System.lineSeparator());

            for (T languageVariousCountries: locales) {
                Path filePath = getFilePath(basePath, basePropertiesFilename, languageVariousCountries);
                if (Files.exists(filePath)) continue;

                Files.createFile(filePath);

                makeTranslateResultWriteToI18NFile(
                        filePath,
                        i18NConfig.keys,
                        collectTranslateResult(api, i18NConfig.keys.size(), i18NConfig.searchQuery.toString(), languageVariousCountries));

                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException | InterruptedException e) { throw new RuntimeException(e); }
    }

    private static Properties getProperties(Path basePath, String basePropertiesFilename) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(basePath.resolve(basePropertiesFilename + ".properties"))) {
            properties.load(in);
        }

        return properties;
    }

    private static I18NConfig collectConfigKeyValue(Properties properties, String separator) {
        I18NConfig i18NConfig = new I18NConfig(properties.size());
        StringBuilder searchQuery = i18NConfig.searchQuery;
        ArrayList<String> keys = i18NConfig.keys;

        int index = 0;
        for (Map.Entry<Object, Object> line: properties.entrySet()) {
            String value = (String) line.getValue();
            if (value == null) continue;
            if (index++ > 0) searchQuery.append(separator);
            searchQuery.append(value);
            keys.add((String) line.getKey());
        }

        if (index < properties.size()) keys.trimToSize();

        return i18NConfig;
    }

    private static Path getFilePath(Path basePath, String basePropertiesFilename, TransApi.LanguageVariousCountries languageVariousCountries) {
        return basePath.resolve(generatorLanguagePropertiesFileName(basePropertiesFilename, languageVariousCountries));
    }

    private static List<String> collectTranslateResult(
            TransApi api,
            int size,
            String searchQuery,
            TransApi.LanguageVariousCountries languageVariousCountries
    ) {
        List<String> values = new ArrayList<>(size);
        JSONObject json = JSON.parseObject(api.getTransResult(searchQuery, "auto", languageVariousCountries.getLanguage()));
        List<Map> result = JSONArray.parseArray(json.getString("trans_result"), Map.class);
        int translateSize = result.size();
        if (translateSize == 1) {
            Scanner scanner = new Scanner((String) result.get(0).get("dst"));
            while (scanner.hasNextLine()) values.add(scanner.nextLine());
        } else if (translateSize > 1) for (Map translateResult: result) values.add((String) translateResult.get("dst"));

        if (values.size() == 0) throw new RuntimeException("找不到对应的翻译结果.");
        return values;
    }

    private static void makeTranslateResultWriteToI18NFile(Path filePath, ArrayList<String> keys, List<String> values) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (int i = 0; i < keys.size(); i++) {
                writer.write(keys.get(i) + "=" + values.get(i));
                writer.newLine();
            }
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private static String generatorLanguagePropertiesFileName(String basePropertiesFilename, TransApi.LanguageVariousCountries locale) {
        String resourceLanguage = locale.getResourceLanguage();
        return basePropertiesFilename + "_" + (resourceLanguage == null ? locale.getLanguage() : resourceLanguage) + ".properties";
    }
}