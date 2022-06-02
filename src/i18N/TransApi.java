package i18N;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    enum LanguageVariousCountries {
        YUE("yue")/* 粤语 */, KOR("kor", "ko")/* 韩语 */, TH("th")/* 泰语 */, PT("pt")/* 葡萄牙语 */, EL("el")/* 希腊语 */, BUL("bul")/* 保加利亚语 */,
        FIN("fin")/* 芬兰语 */, SLO("slo")/* 斯洛文尼亚语 */, CHT("cht")/* 繁体中文 */, ZH("zh")/* 中文 */, FRA("fra")/* 法语 */,
        ARA("ara")/* 阿拉伯语 */, DE("de")/* 德语 */, NL("nl")/* 荷兰语 */, EST("est")/* 爱沙尼亚语 */,
        CS("cs")/* 捷克语 */, SWE("swe")/* 瑞典语 */, VIE("vie")/* 越南语 */, EN("en")/* 英语 */, JP("jp", "ja")/* 日语 */, SPA("spa")/* 西班牙语 */,
        RU("ru")/* 俄语 */, IT("it")/* 意大利语 */, PL("pl")/* 波兰语 */, DAN("dan")/* 丹麦语 */, ROM("rom")/* 罗马尼亚语 */, HU("hu")/* 匈牙利语 */;
        private final String language;
        private final String resourceLanguage;
        LanguageVariousCountries(String language) {
            this(language, null);
        }

        LanguageVariousCountries(String language, String resourceLanguage) {
            this.language = language;
            this.resourceLanguage = resourceLanguage;
        }

        public String getLanguage() { return language; }
        public String getResourceLanguage() { return resourceLanguage; }
    }

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private final String appid;
    private final String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) { return HttpGet.get(TRANS_API_HOST, buildParams(query, from, to)); }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>() {{ put("q", query); put("from", from); put("to", to); put("appid", appid); }};

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }
}
