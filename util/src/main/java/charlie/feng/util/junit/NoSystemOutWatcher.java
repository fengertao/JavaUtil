package charlie.feng.util.junit;

import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by jfeng1 on 2017/3/20.
 */
public class NoSystemOutWatcher extends TestWatcher {

    private final PrintStream stdout = System.out;
    private final PrintStream stderr = System.err;
    private ByteArrayOutputStream bos;

    @Override
    protected void succeeded(Description description) {
        String testOutput = bos.toString();
        recoverOriginalOutput();
        if (description.getAnnotation(AllowSystemOut.class) != null) {
            return;
        }
        if (testOutput.length() > 0) {
            Assert.fail("System output captured:" + testOutput);
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        recoverOriginalOutput();
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        recoverOriginalOutput();
    }

    @Override
    protected void starting(Description description) {
        bos = new ByteArrayOutputStream();
        PrintStream tempOutput = new PrintStream(bos, true);
        System.setOut(tempOutput);
        System.setErr(tempOutput);
    }

    @Override
    protected void finished(Description description) {
        recoverOriginalOutput();
    }

    protected void recoverOriginalOutput() {
        System.err.flush();
        System.out.flush();
        System.setOut(stdout);
        System.setErr(stderr);
    }
}
