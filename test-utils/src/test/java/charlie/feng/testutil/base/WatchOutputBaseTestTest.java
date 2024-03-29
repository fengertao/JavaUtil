package charlie.feng.testutil.base;

import charlie.feng.testutil.annotation.AllowedOutput;
import charlie.feng.testutil.annotation.HiddenOutput;
import org.junit.Test;

public class WatchOutputBaseTestTest extends WatchOutputBaseTest {

    @Test
    public void withoutOutputTest() {
        return;
    }

    @Test
    @AllowedOutput(patterns = {"expected output", "number \\d*"})
    public void withAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @AllowedOutput(patterns = "expected output")
    public void negWithAnnotationNegtiveTest() {
//        Test will fail after uncomment this line
        System.out.println("unexpectedoutput");
        return;
    }


    @Test
    @HiddenOutput(patterns = {"expected output", "number \\d*"})
    public void logWarnWithAnnotationPositiveTest() {
        logger.warn("Hello, this is expected output");
        logger.warn("number 1234567890");
        return;
    }

    @Test
    @HiddenOutput(patterns = {"testException", "java.lang.Exception", "at "})
    public void logWarnExceptionTest() {
        logger.warn("testException", new Exception());
        return;
    }

    @Test
    @HiddenOutput(patterns = "expected output")
    public void negLogWarnWithAnnotationNegtiveTest() {
        //        Test will fail after uncomment this line
        logger.warn("unexpectedoutput");
        return;
    }

    @Test
    public void negTobeFailureTest() {
//        Test will fail after uncomment this line
        System.out.println("Hello, this is output line1");
        System.out.println("Hello, this is output line2");
        return;
    }

    @Test
    public void netTobeFailureMultiOutoutTest() {
//        Test will fail after uncomment this line
        System.out.println("Hello, this is output line1");
        System.out.println("Hello, this is output line2");
        logger.info("Info level message line1");
        logger.info("Info level message line2");
        return;
    }

    @Test
    public void negInfoLevelTest() {
//        Test will fail after uncomment this line
        logger.info("Testing info level message");
        return;
    }


    @Test
    public void debugLevelTest() {
        logger.debug("Testing debug level message line1");
        logger.debug("Testing debug level message line2");
        return;
    }
}
