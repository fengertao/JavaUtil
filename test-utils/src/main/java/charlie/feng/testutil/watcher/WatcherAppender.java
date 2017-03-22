package charlie.feng.testutil.watcher;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

import java.io.StringWriter;
import java.io.Writer;

public class WatcherAppender extends WriterAppender {
    private static volatile WatcherAppender instance;
    private static volatile Writer testWriter;
    private static volatile Layout testLayout;

    public WatcherAppender() {
        testWriter = new StringWriter();
        instance = this;
    }

    public WatcherAppender(Layout layout) {
        testWriter = new StringWriter();
        testLayout = layout;
        setLayout(layout);
        activateOptions();
        instance = this;
    }

    public void activateOptions() {
        setWriter(testWriter);
        super.activateOptions();
    }

    public static String getLoggedMessages() {
        return testWriter.toString();
    }

    public static void clear() {
        testWriter = new StringWriter();
        if (instance != null) {
            instance.setWriter(testWriter);
        }
        if (testLayout != null) {
            instance.setLayout(testLayout);
        }
    }
}
