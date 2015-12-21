package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;

public class TempFileDemo {
	public static void main(String[] args) {
		try {
			Path tempFile = Files.createTempFile(null, ".myapp");
			System.out.format("The temporary file" + " has been created: %s%n", tempFile);
			
			Pattern p = null;
			if (System.getProperty("sun.desktop").equals("windows")){
				  p = Pattern.compile("C:\\\\Users\\\\.*\\\\AppData\\\\Local\\\\Temp\\\\.*\\.myapp");
			} else {
				  p = Pattern.compile("/tmp/.*\\.myapp");
			}
			Matcher m = p.matcher(tempFile.toString());
			if (!m.matches()) {
				throw new RuntimeException("Unexpected temp file location");
			}
			Files.delete(tempFile);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
	}
}
