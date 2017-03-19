package charlie.feng.demo.nio.find;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

/**
 * Created by jfeng1 on 11/25/2016.
 */
public class FindMultiThread {

    public static void main(String[] args) throws Exception {
//        doFind();
        long start = new Date().getTime();
        doFind();
        long end = new Date().getTime();
        System.out.println(end - start);
    }

    private static void doFind() throws IOException {
        if (System.getProperty("sun.desktop").equals("windows")) {
            Path dir = FileSystems.getDefault().getPath("c:\\");
            doFind(dir);
        }
    }

    private static void doFind(Path dir) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                String filename = path.getFileName().toString();
                if (filename.contains("sys")) {
                    System.out.println(path.toAbsolutePath().toString());
                }
                if (Files.isDirectory(path)) {
                    doFind(path);
                }
            }

        } catch (java.nio.file.AccessDeniedException e) {
            //DoNothing
        }
    }
}
