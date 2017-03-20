package charlie.feng.util.edge;

import java.util.Date;

/**
 * Created by jfeng1 on 9/27/2016.
 */
public class DateUtil {
    public static void main(String[] args) {
        long l = 1479153970;
        System.out.println(new Date(l));
        System.out.println(new Date(l * 1000));
    }
}
