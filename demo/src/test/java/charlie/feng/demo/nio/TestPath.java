package charlie.feng.demo.nio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;

import org.junit.Test;

public class TestPath {


	@Test
	public void testEquals() throws IOException{
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
			
		} else {
			Path p1 = Paths.get("/home/Charlie/FileA");
			Path p2 = Paths.get("/home/Charlie/FILEA");
			assertNotEquals("In Linux, Path compare is case senstive", p1, p2);
			assertEquals("Path to URI in Linux is file protocol", p1.toUri().toString(), "file:///home/Charlie/DANIEL/FileA");
			
		}
	}


}
