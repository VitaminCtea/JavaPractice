package randomAccess;

import java.io.*;

public class DataIO
{
    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(size);
        int i = 0;
        boolean isDone = false;
        while (!isDone && i < size) {
            char ch = in.readChar();
            if (ch == 0) isDone = true;
            else stringBuilder.append(ch);
            i++;
        }
        in.skipBytes(2 * (size - i));
        return stringBuilder.toString();
    }

    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) ch = s.charAt(i);
            out.writeChar(ch);
        }
    }
}