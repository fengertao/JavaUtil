package charlie.feng.testutil.base;

import charlie.feng.testutil.watcher.SystemOutWatcher;
import org.junit.Rule;
import org.junit.rules.TestRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parent class of all junit test cases, which will failed whenever there is System.out.println or System.err.println
 */
public class WatchOutputBaseTest {

    protected Logger logger = LoggerFactory.getLogger(WatchOutputBaseTest.class);

    @Rule
    public final TestRule watcher = new SystemOutWatcher() {
    };

}
