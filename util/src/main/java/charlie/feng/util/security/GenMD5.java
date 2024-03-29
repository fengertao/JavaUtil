package charlie.feng.util.security;

public class GenMD5 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:   java -jar GenMD5.jar password");
            System.out.println("Example: java -jar GenMD5.jar raiseMySalary");
            System.exit(0);
        }
        String md5 = getMD5(args[0].getBytes());
        System.out.println(md5);

    }

    public static String getMD5(byte[] source) {
        String s = null;
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
