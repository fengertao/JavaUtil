package charlie.feng.testutil.base;

import charlie.feng.testutil.annotation.HiddenOutput;
import org.junit.Assert;
import org.junit.Test;

public class WatchOutputBaseTestNegTest extends WatchOutputBaseTest {

    @Test
    public void negFailWithoutOutputTest() {
        Assert.fail("To be failed test case");
        return;
    }

    @Test
    public void negFailWithOutputTest() {
        System.out.println("Unspecfic Output");
        Assert.fail("To be failed test case");
        return;
    }

    @Test
    public void negFailWithInfoLogTest() {
        logger.info("Info level log in failed message");
        Assert.fail("To be failed test case");
        return;
    }

    @Test
    public void negFailWithDebugLogTest() {
        logger.debug("Debug level log in failed message");
        Assert.fail("To be failed test case");
        return;
    }

    @Test
    public void negFailWithMixedOutputTest() {
        System.out.println("Unspecfic Output");
        logger.info("Info level log in failed message");
        Assert.fail("To be failed test case");
        return;
    }

    @Test
    @HiddenOutput(patterns = {"expected output", "number \\d*"})
    //Output will not hidden for failed test case.
    public void negWithAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        logger.info("number 1234567890");
        Assert.fail("To be failed test case");
        return;
    }
}
