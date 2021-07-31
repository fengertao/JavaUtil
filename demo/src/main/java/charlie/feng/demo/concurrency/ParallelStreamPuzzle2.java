package charlie.feng.demo.concurrency;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamPuzzle2 {
    public static final Deque<String> trace = new ConcurrentLinkedDeque<>();
//    public static AtomicReference<String> trace2 = new AtomicReference("");

    static class IntGenerator implements Supplier<Integer> {
        private final AtomicInteger current = new AtomicInteger();

        public Integer get() {
//            trace.add(current.get() + ":");
            trace.add(current.get() + ": " + Thread.currentThread().getName());
//            trace2.getAndUpdate( (String str) -> str.concat(" " + current.get()));
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> x = Stream.generate(new IntGenerator()).limit(10).parallel().toList();
        System.out.println(x);
        Files.write(Paths.get("PSP2.txt"), trace);
//        Files.write(Paths.get("PSP2trace2.txt"), trace2.get().getBytes());
    }
}