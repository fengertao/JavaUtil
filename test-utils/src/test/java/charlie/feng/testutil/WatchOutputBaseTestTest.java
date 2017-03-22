package charlie.feng.testutil;

import charlie.feng.testutil.annotation.AllowSystemOut;
import charlie.feng.testutil.base.WatchOutputBaseTest;
import org.junit.Test;

public class WatchOutputBaseTestTest extends WatchOutputBaseTest {

    @Test
    public void withoutOutputTest() {
        return;
    }

    @Test
    @AllowSystemOut(patterns = {"expected output", "number \\d*"})
    public void withAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @AllowSystemOut(patterns = "expected output")
    public void withAnnotationNegtiveTest() {
        //        Test will fail after uncomment this line
//        System.out.println("unexpectedoutput");
        return;
    }

    @Test
    public void tobeFailureTest() {
        //        Test will fail after uncomment this line
//        System.out.println("Hello, this is output");
        return;
    }

    @Test
    public void infoLevelTest() {
        //        Test will fail after uncomment this line
//        logger.info("Testing info level message");
        return;
    }

    @Test
    public void debugLevelTest() {
        logger.debug("Testing debug level message line1");
        logger.debug("Testing debug level message line2");
        return;
    }
}
