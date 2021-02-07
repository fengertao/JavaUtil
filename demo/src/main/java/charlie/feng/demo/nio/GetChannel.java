package charlie.feng.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        String name = "data.txt";
        try (
                FileChannel fc = new FileOutputStream(name)
                        .getChannel()
        ) {
            fc.write(ByteBuffer
                    .wrap("Some text ".getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (
                FileChannel fc = new RandomAccessFile(
                        name, "rw").getChannel()
        ) {
            fc.position(fc.size()); // Move to the end
            fc.write(ByteBuffer
                    .wrap("Some more".getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (
                FileChannel fc = new FileInputStream(name)
                        .getChannel()
        ) {
            ByteBuffer buff = ByteBuffer.allocate(BSIZE);
            fc.read(buff);
            buff.flip();
            while (buff.hasRemaining())
                System.out.write(buff.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
    }
}