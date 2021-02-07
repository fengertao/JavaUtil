package charlie.feng.demo.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReadDemo {

    public static void main(String[] args) throws IOException {
//		demo1();
        demo2();
    }

    private static void demo1() {
        Path file = FileSystems.getDefault().getPath(System.getProperty("user.home")).resolve(".bashrc");
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void demo2() {
        Path file = FileSystems.getDefault().getPath(System.getProperty("user.home")).resolve("ChineseCharUTF8.txt");

        // Defaults to READ
        try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
            ByteBuffer buf = ByteBuffer.allocate(10);

            // Read the bytes with the proper encoding for this platform.  If
            // you skip this step, you might see something that looks like
            // Chinese characters when you expect Latin-style characters.
            String encoding = System.getProperty("file.encoding");
            System.out.println(encoding);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer charBuffer = Charset.forName(encoding).decode(buf);
                System.out.print(charBuffer);
                buf.flip();
            }
        } catch (IOException x) {
            System.out.println("caught exception: " + x);
        }
    }

}
