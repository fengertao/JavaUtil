package charlie.feng.demo.concurrency;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.nio.file.*;

public class ParallelStreamPuzzle2 {
    public static final Deque<String> trace = new ConcurrentLinkedDeque<>();
//    public static AtomicReference<String> trace2 = new AtomicReference("");

    static class IntGenerator implements Supplier<Integer> {
        private AtomicInteger current = new AtomicInteger();

        public Integer get() {
//            trace.add(current.get() + ":");
            trace.add(current.get() + ": " + Thread.currentThread().getName());
//            trace2.getAndUpdate( (String str) -> str.concat(" " + current.get()));
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> x = Stream.generate(new IntGenerator()).limit(10).parallel().collect(Collectors.toList());
        System.out.println(x);
        Files.write(Paths.get("PSP2.txt"), trace);
//        Files.write(Paths.get("PSP2trace2.txt"), trace2.get().getBytes());
    }
}