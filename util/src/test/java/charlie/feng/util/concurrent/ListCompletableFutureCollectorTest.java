package charlie.feng.util.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Another example refer to demo project CompletableFutureAllOf
 */
class ListCompletableFutureCollectorTest {

    @Test
    void collectResult() {
        CompletableFuture<List<Integer>> cf = Stream.of(1, 2, 3).map(CompletableFuture::completedFuture).collect(ListCompletableFutureCollector.toCfList());
        //Exception handling omit here
        cf.whenComplete((iList, ex) -> {
            Assertions.assertEquals(iList.size() , 3);
            Assertions.assertEquals(iList, List.of(1,2,3));
        }).join();
    }

    @Test
    void allComplete() {
        CompletableFuture<Void> cf = Stream.of(1, 2, 3).map(CompletableFuture::completedFuture).collect(ListCompletableFutureCollector.allComplete());
        cf.whenComplete((result, ex) -> Assertions.assertNull(result)).join();
    }
}