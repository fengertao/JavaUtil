package charlie.feng.util.debug;

/**
 * @author Charlie Feng
 */
public class ByteShower {

    public static String toUtf8Bytes(String s) {
        try {
            byte[] bs = s.getBytes("UTF-8");
            return bytes2String(bs);
        } catch (Exception e) {
            e.printStackTrace();
            return "some thing error happends in ByteShower.toUtf8Bytes()";
        }
    }

    public static String toUtf16Bytes(String s) {
        try {
            byte[] bs = s.getBytes("UTF-16");
            return bytes2String(bs);
        } catch (Exception e) {
            e.printStackTrace();
            return "some thing error happends in ByteShower.toUtf8Bytes()";
        }
    }

    public static String bytes2String(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append((byte2String(bytes[i])).toString()).append(" ");
        }
        return sb.toString();
    }

    public static StringBuffer byte2String(byte b) {
        StringBuffer sb = new StringBuffer();
        int i = (int) b;
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

    public static StringBuffer toDecStringBuffer(int i, int length) {
        StringBuffer sb = new StringBuffer(Integer.toString(i).toUpperCase());
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
//		System.err.println((int)(byte)-127);
//		
//		for (int i = -128; i <= 10000; i++) {
//			System.out.println(toHexStringBuffer(i,4));
//			
//		}
    }

}
