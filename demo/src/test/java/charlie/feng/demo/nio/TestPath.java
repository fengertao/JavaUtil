package charlie.feng.demo.nio;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestPath {

    static Path testDir = null;

    @BeforeAll
    public static void setup() throws IOException {
        Path homePath = FileSystems.getDefault().getPath(System.getProperty("user.home"));
        testDir = homePath.resolve("testWatchService");
        if (Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }
        Files.createDirectories(testDir);
        Files.createFile(testDir.resolve("FileA"));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        if (testDir != null && Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }

    }

    @Test
    public void testEquals() throws IOException {
        String osType = System.getProperty("sun.desktop");
        if (osType != null && osType.equals("windows")) {
        } else {
        }
    }


}
