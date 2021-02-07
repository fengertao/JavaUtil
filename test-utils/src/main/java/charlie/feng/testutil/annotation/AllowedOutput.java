package charlie.feng.testutil.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add @AllowedOutput annotation will allow output from Junit test case, as long as those output match defined regex pattern.
 * Output include (1) System.out (2) System.err (3) Log4j log
 * Please notice Debug level log is automatically allowed if enabled.
 * refer to SystemOutWatcher for detail.
 * refer to SystemOutWatcherTest and WatchOutputBaseTestTest for example.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.METHOD
})
public @interface AllowedOutput {
    String[] patterns();
}

