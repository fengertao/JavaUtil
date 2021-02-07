package charlie.feng.demo.nio;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class WatchServiceDemo {

    static Path testDir = null;

    public static void main(String[] args) throws IOException {
        setup();
        watch();
        tearDown();
    }

    public static void setup() throws IOException {
        Path homePath = FileSystems.getDefault().getPath(System.getProperty("user.home"));
        testDir = homePath.resolve("testWatchService");
        if (Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }
        Files.createDirectories(testDir);
    }

    @SuppressWarnings({"unchecked", "restriction"})
    /*
     * Watch the change on a directory
     * This code is work only on windows platform.
     * For linux platform, please refer to below example:
     * http://docs.oracle.com/javase/tutorial/essential/io/examples/WatchDir.java
     */
    public static void watch() throws IOException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        WatchKey key = null;
        try {
            WatchEvent.Kind<?>[] eventKinds = new WatchEvent.Kind<?>[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
            WatchEvent.Modifier[] modifier = null;
            if (System.getProperty("sun.desktop").equals("windows")) {
                modifier = new WatchEvent.Modifier[]{FILE_TREE};
            } else {
                modifier = new WatchEvent.Modifier[0];
            }
            key = testDir.register(watcher, eventKinds, modifier);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (; ; ) {
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();

                Path child = testDir.resolve(filename);
                System.out.format("%s event on Child:%s\n", kind, child);
            }
            boolean valid = key.reset();
            //The loop should exit if the test directory is delete. but during test I have not found the exit.
            if (!valid) {
                break;
            }
        }

    }

    public static void tearDown() throws IOException {
        if (Files.exists(testDir)) {
            FileUtils.deleteDirectory(testDir.toFile());
        }

    }
}
