package charlie.feng.testutil.watcher;

import charlie.feng.testutil.annotation.AllowedOutput;
import charlie.feng.testutil.annotation.HiddenOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class SystemOutWatcherTest {

    @Rule
    public final TestRule watcher = new SystemOutWatcher() {
    };

    @Test
    public void withoutOutputTest() {
        return;
    }

    @Test
    @AllowedOutput(patterns = {"expected output", "number \\d*"})
    public void withAllowedOutputPositiveTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @AllowedOutput(patterns = "expected output")
    public void negAllowedOutputNegtiveTest() {
        //Test will fail after uncomment this line
        System.out.println("unexpectedoutput");
        return;
    }

    @Test
    @HiddenOutput(patterns = {"expected output", "number \\d*"})
    public void withHiddenOutputTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @HiddenOutput(patterns = "expected output")
    public void negHiddenOutputTest() {
        //Test will fail after uncomment this line
        System.out.println("unexpectedoutput");
        return;
    }

    @Test
    public void negTobeFailureTest() {
        //Test will fail after uncomment this line
        System.out.println("Hello, this is output");
        return;
    }
}
