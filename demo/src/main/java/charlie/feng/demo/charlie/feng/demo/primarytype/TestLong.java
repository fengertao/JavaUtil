package charlie.feng.demo.charlie.feng.demo.primarytype;

/**
 * Created by jfeng1 on 2/8/2017.
 */
public class TestLong {

    public static void main(String[] args) {
        String[] testStrs = new String[]{"0", "-1", "1", null};
        for (String s : testStrs) {
            System.out.println(s);
//            System.out.println(new Long(s));
            System.out.println(Long.valueOf(s));
            System.out.println();
        }

    }


}
