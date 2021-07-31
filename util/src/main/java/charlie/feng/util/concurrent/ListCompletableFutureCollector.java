package charlie.feng.util.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListCompletableFutureCollector {

    /**
     * Transforms a <pre>{@code List<CompletableFuture<T>>}</pre> into a <pre>{@code CompletableFuture<List<T>>}</pre>
     * @param <X> the computed result type
     * @param <T> some CompletableFuture
     * @return a CompletableFuture of <pre>{@code CompletableFuture<List<T>>}</pre> that is complete when all collected CompletableFutures are complete.
     */
    public static <X, T extends CompletableFuture<X>> Collector<T, ?, CompletableFuture<List<X>>> toCfList(){
        return Collectors.collectingAndThen(Collectors.toList(), joinResult());
    }

    /**
     * Transforms a <pre>{@code List<CompletableFuture<?>>}</pre> into a <pre>{@code CompletableFuture<Void>}</pre>
     * Use this function if you are not interested in the collected results or the collected CompletableFutures are of
     * type Void.
     * @param <T> some CompletableFuture
     * @return a <pre>{@code CompletableFuture<Void>}</pre> that is complete when all collected CompletableFutures are complete.
     */
    public static <T extends CompletableFuture<?>> Collector<T, ?, CompletableFuture<Void>> allComplete(){
        return Collectors.collectingAndThen(Collectors.toList(), ListCompletableFutureCollector::allOf);
    }

    private static <X, T extends CompletableFuture<X>> Function<List<T>, CompletableFuture<List<X>>> joinResult() {
        return ls -> allOf(ls)
                .thenApply(v -> ls.stream()
                        .map(CompletableFuture::join)
                        .toList());
    }

    private static <T extends CompletableFuture<?>> CompletableFuture<Void> allOf(List<T> ls) {
        return CompletableFuture.allOf(ls.toArray(new CompletableFuture[ls.size()]));
    }


    /**
     * Transforms a <pre>{@code List<CompletableFuture<T>>}</pre> into a <pre>{@code CompletableFuture<List<T>>}</pre>
     * This method totally work, but it is not streaming style, so deprecated and suggest use {@code toCfList}
     * @param <T> the computed result type
     * @param cfList the source List of CompletableFuture
     * @return a CompletableFuture of <pre>{@code CompletableFuture<List<T>>}</pre> that is complete when all collected CompletableFutures are complete.
     */
    @Deprecated
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> cfList) {
        return CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]))
                .thenApply(v -> cfList.stream()
                        .map(CompletableFuture::join)
                        .toList()
                );
    }

}
