package charlie.feng.testutil.watcher;

import charlie.feng.testutil.annotation.AllowedOutput;
import charlie.feng.testutil.annotation.HiddenOutput;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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
 */
public class SystemOutWatcher extends TestWatcher {

    private final PrintStream stdout = System.out;
    private final PrintStream stderr = System.err;
    private Appender origConsoleAppender;
    private ByteArrayOutputStream bos;
    protected Logger logger = Logger.getLogger(SystemOutWatcher.class);

    @Override
    protected void succeeded(Description description) {
        boolean isUnexpectedOutput = false;
        String unexpectedOutput = "";
        String systemOutput = bos.toString();
        recoverOriginalOutput();

        if (systemOutput.length() > 0) {
            String[] outputs = systemOutput.split("\n|\r");
            for (String output : outputs) {
                if (output.length() == 0) {
                } else if (isHiddenLog(output, description)) {
                    //do nothing and hidden the log
                } else if (isAllowedLog(output, description)) {
                    System.out.println(output);
                } else {
                    isUnexpectedOutput = true;
                    unexpectedOutput += "[Unexpected Log]" + output + "\n";
                }
            }
        }

        String allLog = WatcherAppender.getLoggedMessages();
        String[] logs = allLog.split("\n|\r");
        for (String log : logs) {
            if (log.length() == 0) {
            } else if (isHiddenLog(log, description)) {
                //do nothing and hidden the log
            } else if (isAllowedLog(log, description)) {
                System.out.println(log);
            } else {
                isUnexpectedOutput = true;
                unexpectedOutput += "[Unexpected Log]" + log + "\n";
            }
        }

        // Known issue 1: Allowed system out will display twice
        // Known issue 2: System out and log4j log are out of order.
        // Both issues are acceptable, Because System out is existing in bad quality project only.
        if (isUnexpectedOutput) {
            Assert.fail(unexpectedOutput);
        }
    }

    private boolean isHiddenLog(String log, Description description) {
        //SLF4J warning can be hidden
        if  (log.startsWith("SLF4J:")) {
            return true;
        }

        HiddenOutput outputAnnotation = description.getAnnotation(HiddenOutput.class) ;
        if ((outputAnnotation == null) || (outputAnnotation.patterns() == null) || (outputAnnotation.patterns().length == 0)) {
            return false;
        }
        String[] patterns = outputAnnotation.patterns();
        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
    private boolean isAllowedLog(String log, Description description) {
        //Debug level log in test case is accepted
        if  (log.startsWith("[Log4j DEBUG:]")) {
            return true;
        }

        AllowedOutput outputAnnotation = description.getAnnotation(AllowedOutput.class) ;
        if ((outputAnnotation == null) || (outputAnnotation.patterns() == null) || (outputAnnotation.patterns().length == 0)) {
            return false;
        }
        String[] patterns = outputAnnotation.patterns();
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
        String systemOutput = bos.toString();
        recoverOriginalOutput();
        //Log4j output are displayed after system output
        if (systemOutput.length() > 0) {
            System.out.print(systemOutput);
        }
        String allLog = WatcherAppender.getLoggedMessages();
        System.out.print(allLog);
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        String systemOutput = bos.toString();
        recoverOriginalOutput();
        //Log4j output are displayed after system output
        if (systemOutput.length() > 0) {
            System.out.print(systemOutput);
        }
        String allLog = WatcherAppender.getLoggedMessages();
        System.out.print(allLog);
    }

    @Override
    protected void starting(Description description) {

        bos = new ByteArrayOutputStream();
        PrintStream tempOutput = new PrintStream(bos, true);
        System.setOut(tempOutput);
        System.setErr(tempOutput);
        WatcherAppender.clear();
        org.apache.log4j.Logger rootLogger = LogManager.getRootLogger();
        Appender consoleAppender = rootLogger.getAppender("CONSOLE");
        if (consoleAppender != null) {
            origConsoleAppender = consoleAppender;
            LogManager.getRootLogger().removeAppender(consoleAppender);
        }
    }

    @Override
    protected void finished(Description description) {
        recoverOriginalOutput();
    }

    protected void recoverOriginalOutput() {
        System.out.flush();
        System.err.flush();
        System.setOut(stdout);
        System.setErr(stderr);
        org.apache.log4j.Logger rootLogger = LogManager.getRootLogger();
        if ((rootLogger.getAppender("CONSOLE") == null) && (origConsoleAppender != null)) {
            LogManager.getRootLogger().addAppender(origConsoleAppender);
        }
    }
}
