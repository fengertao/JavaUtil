package charlie.feng.testutil.watcher;

import charlie.feng.testutil.annotation.AllowSystemOut;
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
    @AllowSystemOut(patterns = {"expected output", "number \\d*"})
    public void withAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @AllowSystemOut(patterns = "expected output")
    public void withAnnotationNegtiveTest() {
        //Test will fail after uncomment this line
        //        System.out.println("unexpectedoutput");
        return;
    }

    @Test
    public void tobeFailureTest() {
        //Test will fail after uncomment this line
        //        System.out.println("Hello, this is output");
        return;
    }
}
