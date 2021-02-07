package charlie.feng.demo.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletedMachina {

    public static void main(String[] args) {
        CompletableFuture<Machina> cf = CompletableFuture.completedFuture(new Machina(0));
        ExecutorService executor = Executors.newFixedThreadPool(10);
//        try {
//            System.out.println(cf.get());
        cf.thenApplyAsync(Machina::work, executor)
                .thenApply(Machina::work)
                .thenApplyAsync(Machina::work, executor)
                .thenApply(Machina::work)
                .thenApplyAsync(Machina::work, executor)
                .thenApplyAsync(Machina::work, executor)
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work);

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        new Nap(2);

        executor.shutdown();

    }
}
