package charlie.feng.demo.nio.find;

import charlie.feng.util.edge.DateUtil;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jfeng1 on 11/25/2016.
 */
public class FindSingleThread {
    static long start;

    public static void main(String[] args) throws Exception {
//        doFind();
        start = System.currentTimeMillis();
        doFind();
        while(Thread.activeCount() >2) {
//            System.out.println("Total Threads:" + Thread.activeCount());
            Thread.yield();
        }
        System.out.println(System.currentTimeMillis() - start);

    }

    private static void doFind() throws IOException {
        if (System.getProperty("sun.desktop").equals("windows")) {
            Path dir = FileSystems.getDefault().getPath(System.getProperty("user.home"));
            dir = FileSystems.getDefault().getPath("c:\\");
            doFind(dir);
        }
    }

    private static void doFind(Path dir) throws IOException {
        FindThread findThread = new FindThread(dir);
        new Thread(findThread).start();
    }
}

class FindThread implements Runnable {

    Path currentPath;

    public FindThread(Path currentPath) {
        this.currentPath = currentPath;

    }

    @Override public void run() {
        try {
            for (Path path : Files.newDirectoryStream(currentPath)) {
                String filename = path.getFileName().toString();
                if (filename.contains("sys")) {
//                    System.out.println(path.toAbsolutePath().toString());
                }
                if (Files.isDirectory(path)) {
                    FindThread findThread = new FindThread(path);
                    new Thread(findThread).start();
                }
            }
        }catch (IOException e) {

        }

    }
}
