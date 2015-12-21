package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.file.*;

public class TempFileDemo {
	public static void main(String[] args) {
		try {
			Path tempFile = Files.createTempFile(null, ".myapp");
			System.out.format("The temporary file" + " has been created: %s%n", tempFile);
			Files.delete(tempFile);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
}
