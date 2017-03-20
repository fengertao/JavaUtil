package charlie.feng.demo.nio;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestPath {

    static Path testDir = null;

    @BeforeClass
    public static void setup() throws IOException {
        Path homePath = FileSystems.getDefault().getPath(System.getProperty("user.home"));
        testDir = homePath.resolve("testWatchService");
        if (Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }
        Files.createDirectories(testDir);
        Files.createFile(testDir.resolve("FileA"));
    }

    @AfterClass
    public static void tearDown() throws IOException {
        if (Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }

    }

    @Test
    public void testEquals() throws IOException {
        if (System.getProperty("sun.desktop").equals("windows")) {
            Path p1 = Paths.get("c:\\Users\\Daniel\\FILEA");
            Path p2 = Paths.get("C:\\Users\\DANIEL\\FileA");
            Path p3 = Paths.get("C:\\Users\\Daniel\\..\\Daniel\\FILEA");
            Path p4 = Paths.get("C:\\Users\\Daniel\\..\\Charlie\\FILEA");
            Path p5 = Paths.get("C:\\Users\\Daniel\\.bashRc");

            assertEquals("In windows, Path compare is case insenstive", p1, p2);
            assertNotEquals("Path is not normalized before compare", p1, p3);
            assertEquals("Path is not normalized before compare", p1, p3.normalize());
            assertTrue("Path is not normalized before startsWith", p4.startsWith("C:\\Users\\Daniel"));
            assertTrue("Path is not normalized before startsWith", p4.startsWith("C:\\Users\\Daniel\\.."));
            assertTrue("Path is not normalized before startsWith", p4.startsWith("C:\\Users\\Daniel\\..\\Charlie"));
            assertFalse("Path is not normalized before startsWith", p4.normalize().startsWith("C:\\Users\\Daniel"));
            assertFalse("Path is not normalized before startsWith", p4.normalize().startsWith("C:\\Users\\Daniel\\.."));
            assertFalse("Path is not normalized before startsWith", p4.normalize().startsWith("C:\\Users\\Daniel\\..\\Charlie"));
            assertEquals("Path to URI in windows is file protocol", p2.toUri().toString(), "file:///C:/Users/DANIEL/FileA");
            assertEquals("Path to URI is not normalized", p4.toUri().toString(), "file:///C:/Users/Daniel/../Charlie/FILEA");

            assertEquals("GetRoot in windows return driver", p1.getRoot().toString().toUpperCase(), "C:\\");

            //Run this line only if login as Daniel, and setup env
            //assertTrue("Path is not normalized before isSameFile", Files.isSameFile(Paths.get("C:\\Users\\Daniel\\..\\Daniel\\testWatchService"), Paths.get("C:\\Users\\Daniel\\testWatchService")));
            //assertTrue("Symlink is same file ", Files.isSameFile(Paths.get("C:\\Users\\Daniel\\Music"), Paths.get("C:\\Users\\Daniel\\Test3")));

        } else {
            Path p1 = Paths.get("/home/Charlie/FileA");
            Path p2 = Paths.get("/home/Charlie/FILEA");
            assertNotEquals("In Linux, Path compare is case senstive", p1, p2);
            assertEquals("Path to URI in Linux is file protocol", p1.toUri().toString(), "file:///home/Charlie/DANIEL/FileA");

        }
    }


}
