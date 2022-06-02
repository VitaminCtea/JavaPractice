package i18N;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 调用百度翻译API示例
public class MD5 {
    // 首先初始化一个字符数组，用来存放每个16进制字符
    private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String md5(String input) {
        if (input == null) return null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            // 输入的字符串转换成字节数组
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) { return null; }
    }

    public static String md5(File file) {
        if (!file.isFile()) {
            System.err.println("文件" + file.getAbsolutePath() + "不存在或者不是文件");
            return null;
        }

        try (FileInputStream in = new FileInputStream(file)) { return md5(in); } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public static String md5(InputStream in) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) messagedigest.update(buffer, 0, read);
            in.close();

            return byteArrayToHex(messagedigest.digest());
        } catch (NoSuchAlgorithmException | IOException e) { e.printStackTrace(); }

        return null;
    }

    private static String byteArrayToHex(byte[] byteArray) {
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
