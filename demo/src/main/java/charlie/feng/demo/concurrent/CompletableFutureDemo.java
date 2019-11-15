package charlie.feng.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class CompletableFutureDemo {
    public static final Logger logger = LoggerFactory.getLogger("CompletableFutureDemo");

    public static void main(String[] args) {
        completeExceptionallyExample();
        applyVsThenCompose();
    }

    static CompletableFuture<Integer> add100(Integer i){
        return CompletableFuture.supplyAsync(() -> 100 + i);
    }
    static CompletableFuture<Integer> add1000(Integer i){
        return CompletableFuture.supplyAsync(() -> 1000 + i);
    }
    static void applyVsThenCompose() {

        CompletableFuture<Integer> applyResult = CompletableFuture.completedFuture(0).thenApply(s -> s + 1).thenApply(s -> s + 10);
        assertEquals(applyResult.join(), 11);

        CompletableFuture<Integer> thenComposeResult = CompletableFuture.completedFuture(0).thenCompose(CompletableFutureDemo::add100).thenCompose(CompletableFutureDemo::add1000);
        assertEquals(thenComposeResult.join(), 1100);

        CompletableFuture<Integer> whenCompleteResult = CompletableFuture.completedFuture(8).whenComplete((s, ex) -> {logger.info("whenComplete1:"+s);}).whenComplete((s, ex) -> {logger.info("whenComplete2:"+s);});
    }

    static void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync((String msg) -> {
                    logger.trace("mark0");
                    return msg.toUpperCase();
                },
                CompletableFuture.delayedExecutor(2000, TimeUnit.SECONDS));
        CompletableFuture exceptionHandler = cf.handle((s, th) -> {
            logger.trace("mark1");
            return (th != null) ? "message upon cancel" : "";
        });
        logger.trace("mark2");

        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        logger.trace("mark3");

        assertTrue(cf.isCompletedExceptionally(), "Was not completed exceptionally");
        try {
            logger.trace("mark1");
            cf.join();
            logger.trace("mark5");

            fail("Should have thrown an exception");
            logger.trace("mark6");

        } catch (CompletionException ex) { // just for testing
            logger.trace("mark7");

            assertEquals(ex.getCause().getMessage(), "completed exceptionally");
            logger.trace("mark8");

        }
        logger.trace("mark9");

        assertEquals(exceptionHandler.join(), "message upon cancel");
    }
}
