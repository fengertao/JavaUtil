package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
                assertContainsContents(stream);
            }

            DirectoryStream.Filter<Path> dirFilter = file -> (Files.isDirectory(file));
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, dirFilter)) {
                assertContainsContents(stream);
            }
        }

    }

    private static void assertContainsContents(DirectoryStream<Path> stream) {
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
