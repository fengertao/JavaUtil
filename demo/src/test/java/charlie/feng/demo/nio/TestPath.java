package charlie.feng.demo.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TestPath {

//	@Test
//	public void testRelativize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToUri() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToAbsolutePath() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToRealPath() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCompareToPath() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testEquals() {
//		System.getProperties().list(System.out);
		if (System.getProperty("sun.desktop").equals("windows")) {
			Path p1 = Paths.get("C:\\Users\\Daniel\\FILEA");
			Path p2 = Paths.get("C:\\Users\\DANIEL\\FileA");
			System.out.println(p1.equals(p2));
		} else {
			Path p1 = Paths.get("/home/Charlie/FileA");
			Path p2 = Paths.get("/home/Charlie/FILEA");
			System.out.println(p1.equals(p2));
			
		}
	}

//	@Test
//	public void testHashCode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCompareToT() {
//		fail("Not yet implemented");
//	}

}
