package charlie.feng.util.junit;

import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by jfeng1 on 2017/3/20.
 */
public class NoSystemOutTest {

    @Rule
    public final TestRule watcher = new NoSystemOutWatcher() {};

    @Test
    public void withoutOutputTest() {
        return;
    }

    @Test
    @AllowSystemOut
    public void withAnnotationTest() {
        System.out.println("Hello, this is output");
        return;
    }
    @Test
    public void tobeFailureTest() {
        //Test will fail after uncomment this line
        //System.out.println("Hello, this is output");
        return;
    }
}
