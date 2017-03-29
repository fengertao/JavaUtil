package charlie.feng.testutil.base;

import charlie.feng.testutil.annotation.AllowedOutput;
import charlie.feng.testutil.annotation.HiddenOutput;
import org.junit.Assert;
import org.junit.Test;

public class WatchOutputBaseTestNegTest extends WatchOutputBaseTest {

    @Test
    public void negFailWithoutOutputTest() {
        Assert.assertTrue("To be failed test case", false);
        return;
    }

    @Test
    public void negFailWithOutputTest() {
        System.out.println("Unspecfic Output");
        Assert.assertTrue("To be failed test case", false);
        return;
    }

    @Test
    public void negFailWithInfoLogTest() {
        logger.info("Info level log in failed message");
        Assert.assertTrue("To be failed test case", false);
        return;
    }

    @Test
    public void negFailWithDebugLogTest() {
        logger.debug("Debug level log in failed message");
        Assert.assertTrue("To be failed test case", false);
        return;
    }

    @Test
    public void negFailWithMixedOutputTest() {
        System.out.println("Unspecfic Output");
        logger.info("Info level log in failed message");
        Assert.assertTrue("To be failed test case", false);
        return;
    }

    @Test
    @HiddenOutput(patterns = {"expected output", "number \\d*"})
    //Output will not hidden for failed test case.
    public void negWithAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        logger.info("number 1234567890");
        Assert.assertTrue("To be failed test case", false);
        return;
    }
}
