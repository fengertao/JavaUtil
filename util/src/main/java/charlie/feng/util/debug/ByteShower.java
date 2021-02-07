package charlie.feng.util.debug;

import java.nio.charset.StandardCharsets;

/**
 * @author Charlie Feng
 */
public class ByteShower {

    public static String toUtf8Bytes(String s) {
        try {
            byte[] bs = s.getBytes(StandardCharsets.UTF_8);
            return bytes2String(bs);
        } catch (Exception e) {
            e.printStackTrace();
            return "some thing error happends in ByteShower.toUtf8Bytes()";
        }
    }

    public static String toUtf16Bytes(String s) {
        try {
            byte[] bs = s.getBytes(StandardCharsets.UTF_16);
            return bytes2String(bs);
        } catch (Exception e) {
            e.printStackTrace();
            return "some thing error happends in ByteShower.toUtf8Bytes()";
        }
    }

    public static String bytes2String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append((byte2String(aByte)).toString()).append(" ");
        }
        return sb.toString();
    }

    public static StringBuffer byte2String(byte b) {
        StringBuffer sb = new StringBuffer();
        int i = b;
        if (i < 0) i += 256;

        sb.append((toHexStringBuffer(i, 2).toString()));
        return sb;
    }

    public static StringBuffer toHexStringBuffer(int i, int length) {
        StringBuffer sb = new StringBuffer(Integer.toHexString(i).toUpperCase());
        char[] leadingZero = new char[length];
        for (int j = 0; j < length; j++) {
            leadingZero[j] = '0';
        }
        sb.insert(0, leadingZero);
        sb.delete(0, sb.length() - length);
        //sb.setLength(length);
        return sb;
    }

    public static void main(String[] args) {
        System.out.println(toUtf16Bytes("a"));
        System.out.println(toUtf8Bytes("a"));
        System.out.println(toUtf16Bytes("\u5EF6"));
        System.out.println(toUtf8Bytes("\u5EF6"));
    }

}
