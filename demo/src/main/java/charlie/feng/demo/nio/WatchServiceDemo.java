package charlie.feng.demo.nio;

import static com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE;
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

import org.apache.commons.io.FileUtils;

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

	@SuppressWarnings("unchecked")
	public static void watch() throws IOException {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		WatchKey key = null;
		try {
			
			WatchEvent.Kind<?>[] eventKinds = new WatchEvent.Kind<?>[] {ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
			key = testDir.register(watcher, eventKinds, FILE_TREE);
		} catch (IOException x) {
			System.err.println(x);
		}

		for (;;) {
			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				System.out.println(e);
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
				System.out.format("%s event on Child:%s\n",kind,child);
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
