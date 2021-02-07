package charlie.feng.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.READ;

public class FileAsyncIODemo {

    public static void main(String[] args) throws Exception {
        readWithFuture();
        readWithCompletionHandler();
    }

    private static void readWithFuture() throws Exception {
        Path file = FileSystems.getDefault().getPath(System.getProperty("user.home")).resolve(".bashrc");
        try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(file, READ)) {
            int fileSize = (int) afc.size();
            ByteBuffer buffer = ByteBuffer.allocate(fileSize);
            Future<Integer> future = afc.read(buffer, 0);
            // In this example, Current thread just hang there waiting IO
            // complete.
            future.get();
            outputByteBuffer(buffer);
        }
    }

    public static void outputByteBuffer(ByteBuffer buffer) {
        byte[] byteData = buffer.array();
        Charset cs = StandardCharsets.UTF_8;
        String data = new String(byteData, cs);
        System.out.println(data);
    }

    private static void readWithCompletionHandler() throws Exception {
        Path file = FileSystems.getDefault().getPath(System.getProperty("user.home")).resolve(".bashrc");
        AsynchronousFileChannel afc = AsynchronousFileChannel.open(file, READ);
        int fileSize = (int) afc.size();
        ByteBuffer buffer = ByteBuffer.allocate(fileSize);
        Attachment attachment = new Attachment(file, buffer, afc);
        CompletionHandler<Integer, Attachment> handler = new PrintFileHandler();
        afc.read(buffer, 0L, attachment, handler);
        System.out.println("Sleeping enough time, longer then the execution of handler");
        Thread.sleep(1000);
    }
}

class Attachment {
    public Path path;
    public ByteBuffer buffer;
    public AsynchronousFileChannel asyncChannel;

    public Attachment(Path path, ByteBuffer buffer, AsynchronousFileChannel asyncChannel) {
        this.path = path;
        this.buffer = buffer;
        this.asyncChannel = asyncChannel;
    }
}

class PrintFileHandler implements CompletionHandler<Integer, Attachment> {

    public void completed(Integer result, Attachment attachment) {
        System.out.format("%s bytes read   from  %s%n", result, attachment.path);
        System.out.format("Read data is:%n");
        FileAsyncIODemo.outputByteBuffer(attachment.buffer);
        try {
            // Close the channel
            attachment.asyncChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void failed(Throwable ex, Attachment attachment) {
        System.err.format("Read operation  on  %s  file failed." + "The  error is: %s%n", attachment.path,
                ex.getMessage());
        try {
            // Close the channel
            attachment.asyncChannel.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
