package charlie.feng.testutil.watcher;

import charlie.feng.testutil.annotation.AllowedOutput;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;

public class WithoutWatcherTest {

    @Test
    public void withoutOutputTest() {
        return;
    }

    @Test
    @AllowedOutput(patterns = {"expected output", "number \\d*"})
    public void withAnnotationPositiveTest() {
        System.out.println("Hello, this is expected output");
        System.out.println("number 1234567890");
        return;
    }

    @Test
    @AllowedOutput(patterns = "expected output")
    public void withAnnotationNegtiveTest() {
        //Test will fail after uncomment this line
        System.out.println("unexpectedoutput");
        return;
    }

    @Test
    public void systemOutTest() {
        //Test will fail after uncomment this line
        System.out.println("Hello, this is output");
        return;
    }

    @Test
    public void errorLogTest() {
        Logger logger = org.slf4j.LoggerFactory.getLogger(ArrayList.class);
        logger.error("Error log4j message");
        return;
    }

    @Test
    public void debugLogTest() {
        Logger logger = org.slf4j.LoggerFactory.getLogger(ArrayList.class);
        logger.debug("Debug log4j message");
        return;
    }

    @Test
    public void infoLogTest() {
        Logger logger = org.slf4j.LoggerFactory.getLogger(ArrayList.class);
        logger.info("Info log4j message");
        return;
    }

}
