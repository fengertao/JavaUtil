package charlie.feng.testutil.base;

import charlie.feng.testutil.watcher.SystemOutWatcher;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestRule;


/**
 * Parent class of all junit test cases, which will failed whenever there is unexpected System.out or System.err or log4j log (INFO level or above)
 * This class is not thread-safe. do not use this class if your project run junit in multi-thread.
 */
public class WatchOutputBaseTest {
    protected Logger logger = Logger.getLogger(WatchOutputBaseTest.class);

    @Rule
    public final TestRule watcher = new SystemOutWatcher() {
    };

}
