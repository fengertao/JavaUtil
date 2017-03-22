package charlie.feng.testutil.watcher;

import charlie.feng.testutil.annotation.AllowSystemOut;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Junit Watcher for System Out
 * Add below lines into junit test class, to detect all System.out.print and System.err.print
 *
 * @Rule public final TestRule watcher = new NoSystemOutWatcher() {};
 * <p>
 * Any system.out.print or System.err.print will fail the test case.
 * Add @AllowSystemOut annotation will disable system out/err check.
 * <p>
 * *This class is not thread-safe. do not use this class if your project run junit in multi-thread.
 * <p>
 */
public class SystemOutWatcher extends TestWatcher {

    private final PrintStream stdout = System.out;
    private final PrintStream stderr = System.err;
    private ByteArrayOutputStream bos;
    protected Logger logger = LoggerFactory.getLogger(SystemOutWatcher.class);

    @Override
    protected void succeeded(Description description) {
        String systemOutput = bos.toString();
        recoverOriginalOutput();
        if (systemOutput.length() > 0) {
            String[] outputs = systemOutput.split("\n|\r");
            for (String output : outputs) {
                if (output.length() == 0) {
                } else if (isExceptedLog(output, description)) {
                    logger.info(output);
                } else {
                    Assert.fail("[Unexpected Output] " + output);
                }
            }
        }
        String allLog = WatcherAppender.getLoggedMessages();
        String[] logs = allLog.split("\n|\r");
        for (String log : logs) {
            if (log.length() == 0) {
            } else if (isExceptedLog(log, description)) {
                logger.info(log);
            } else {
                Assert.fail("[Unexpected Log] " + log);
            }
        }
    }

    private boolean isExceptedLog(String log, Description description) {
        //Debug level log in test case is excepted
        if (log.startsWith("[Log4j DEBUG:]")) {
            return true;
        }

        AllowSystemOut allowSystemOut = description.getAnnotation(AllowSystemOut.class);
        if ((allowSystemOut == null) || (allowSystemOut.patterns() == null) || (allowSystemOut.patterns().length == 0)) {
            return false;
        }
        String[] patterns = allowSystemOut.patterns();
        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
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
        WatcherAppender.clear();
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
