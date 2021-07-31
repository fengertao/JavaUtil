package charlie.feng.demo.nio.find;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jfeng1 on 11/25/2016.
 */
public class FindThreadPool {
    public static ExecutorService es;
    static long start;

    public static void main(String[] args) throws Exception {
        start = System.currentTimeMillis();
        doFind();
        while (((ThreadPoolExecutor) es).getActiveCount() > 0) {
            Thread.yield();
        }
        System.out.println(System.currentTimeMillis() - start);

    }

    private static void doFind() throws IOException {
        es = Executors.newFixedThreadPool(17);
        if (System.getProperty("sun.desktop").equals("windows")) {
            Path dir = FileSystems.getDefault().getPath(System.getProperty("user.home"));
            dir = FileSystems.getDefault().getPath("c:\\");
            doFind(dir);
        }
    }

    private static void doFind(Path dir) throws IOException {
        FindRunable findRunable = new FindRunable(dir);
        es.execute(findRunable);
    }
}

class FindRunable implements Runnable {

    Path currentPath;

    public FindRunable(Path currentPath) {
        this.currentPath = currentPath;

    }

    @Override
    public void run() {
        try {
            for (Path path : Files.newDirectoryStream(currentPath)) {
                String filename = path.getFileName().toString();
                if (filename.contains("sys")) {
                    System.out.println(path.toAbsolutePath());
                }
                if (Files.isDirectory(path)) {
                    FindRunable findRunable = new FindRunable(path);
                    FindThreadPool.es.execute(findRunable);
                }
            }
        } catch (IOException e) {

        }

    }
}
