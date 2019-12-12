package charlie.feng.demo.future;

import java.util.concurrent.CompletableFuture;

public class CompletableDemo {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
        future.join();
//        future.get();
    }
}
