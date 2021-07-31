package charlie.feng.demo.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("w*\\.[a-zA-Z0-9]*", Pattern.MULTILINE);
        System.out.println("flags:" + pattern.flags());
        System.out.println("pattern :" + pattern.pattern());
        System.out.println("toString:" + pattern);
        Matcher m = pattern.matcher("www.charlie");
        System.out.println(m.groupCount());
        boolean b = m.matches();
        System.out.println("matched:" + b);
    }
}
