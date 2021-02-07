package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileAttributeDemo {

    public static void main(String[] args) throws IOException {


//		Exception in thread "main" java.nio.file.NoSuchFileException: C:\Users\Bob\NotExistingFile
//		Path file = Paths.get("C:\\Users\\Bob\\NotExistingFile");
//		Exception in thread "main" java.nio.file.AccessDeniedException: C:\Users\Bob\NoAccessFile
//		Path file = Paths.get("C:\\Users\\Bob\\NoAccessFile");
//		Accessable in mapped drider
//		Path file = Paths.get("Y:\\private\\cfeng\\postman_dump_20150325.txt");
//		Accessable in  network path
        Path file = Paths.get("\\\\10.110.173.66\\exchange\\private\\cfeng\\postman_dump_20150325.txt");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());

        FileStore store = Files.getFileStore(file);

        long total = store.getTotalSpace() / 1024;
        long used = (store.getTotalSpace() -
                store.getUnallocatedSpace()) / 1024;
        long avail = store.getUsableSpace() / 1024;
        System.out.println();
        System.out.println("total: " + total);
        System.out.println("used: " + used);
        System.out.println("avail: " + avail);


    }

}
