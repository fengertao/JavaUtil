package charlie.feng.demo.concurrency;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

public class Summing {
    static void timeTest(String id, long checkValue,
                         LongSupplier operation) {
        System.out.print(id + ": ");
        Timer timer = new Timer();
        long result = operation.getAsLong();
        if (result == checkValue)
            System.out.println(timer.duration() + "ms");
        else
            System.out.format("result: %d%ncheckValue: %d%n",
                    result, checkValue);
    }

    public static final long SZ = 100_000_000L;
    // This even works:
    // public static final int SZ = 1_000_000_000;
    public static final long CHECK =
            SZ * (SZ + 1) / 2; // Gauss's formula

    public static void main(String[] args) {
        System.out.println(CHECK);
        timeTest("Sum Stream", CHECK, () ->
                LongStream.rangeClosed(0, SZ).sum());
        timeTest("Sum Stream Parallel", CHECK, () ->
                LongStream.rangeClosed(0, SZ).parallel().sum());
        timeTest("Sum Iterated", CHECK, () ->
                LongStream.iterate(0, i -> i + 1)
                        .limit(SZ + 1).sum());
        // Slower & runs out of memory above 1_000_000:
        //parallel make the code slower.
        timeTest("Sum Iterated Parallel", CHECK, () ->
                LongStream.iterate(0, i -> i + 1)
                        .parallel()
                        .limit(SZ + 1).sum());
    }
}