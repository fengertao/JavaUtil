package charlie.feng.testutil.base;

import charlie.feng.testutil.watcher.SystemOutWatcher;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestRule;


/**
 * Parent class of all junit test cases, which will failed whenever there is System.out.println or System.err.println
 */
public class WatchOutputBaseTest {
    protected Logger logger = Logger.getLogger(WatchOutputBaseTest.class);

    @Rule
    public final TestRule watcher = new SystemOutWatcher() {
    };

}
