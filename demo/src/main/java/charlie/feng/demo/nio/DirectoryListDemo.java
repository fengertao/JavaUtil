package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DirectoryListDemo {

	// run with java –ea (enable assertion)
	public static void main(String[] args) throws Exception {

		if (System.getProperty("sun.desktop").equals("windows")) {
			Path dir = FileSystems.getDefault().getPath(System.getProperty("user.home"));
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
				List<String> filenameList = new ArrayList<>();
				for (Path file : stream) {
					filenameList.add(file.getFileName().toString());
				}
				assert filenameList.contains(".bashrc");
				assert filenameList.contains("AppData");
				assert filenameList.contains("Music");
				assert filenameList.contains("My Documents");
			}
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "M*")) {
				List<String> filenameList = new ArrayList<>();
				for (Path file : stream) {
					filenameList.add(file.getFileName().toString());
				}
				assert !filenameList.contains(".bashrc");
				assert !filenameList.contains("AppData");
				assert filenameList.contains("Music");
				assert filenameList.contains("My Documents");
			}
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, ".*")) {
				List<String> filenameList = new ArrayList<>();
				for (Path file : stream) {
					filenameList.add(file.getFileName().toString());
				}
				assert filenameList.contains(".bashrc");
				assert !filenameList.contains("AppData");
				assert !filenameList.contains("Music");
				assert !filenameList.contains("My Documents");
			}
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "{M,A}*")) {
				List<String> filenameList = new ArrayList<>();
				for (Path file : stream) {
					filenameList.add(file.getFileName().toString());
				}
				assert !filenameList.contains(".bashrc");
				assert filenameList.contains("AppData");
				assert filenameList.contains("Music");
				assert filenameList.contains("My Documents");
			}

			DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>() {
				public boolean accept(Path file) throws IOException {
					return (Files.isDirectory(file));
				}
			};
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, dirFilter)) {
				List<String> filenameList = new ArrayList<>();
				for (Path file : stream) {
					filenameList.add(file.getFileName().toString());
				}
				assert !filenameList.contains(".bashrc");
				assert filenameList.contains("AppData");
				assert filenameList.contains("Music");
				assert filenameList.contains("My Documents");
			}
			

		}

	}
}
