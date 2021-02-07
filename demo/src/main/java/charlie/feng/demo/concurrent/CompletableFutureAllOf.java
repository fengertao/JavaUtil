package charlie.feng.demo.concurrent;

import charlie.feng.util.concurrent.ListCompletableFutureCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 模拟网络上查汽车的评价
 * 先rest call,3s后得到汽车型号清单
 * 再对每个汽车型号请求评价，请求同时发送。每个请求需要5s得到结果。应为是并行操作，一共只需要5秒
 * 其中sequence方法可以把List<CompletableFuture<T>> 转为 CompletableFuture<List<T>>
 */
public class CompletableFutureAllOf {
    public static final Logger logger = LoggerFactory.getLogger(CompletableFutureAllOf.class);

    public static CompletableFuture<List<Car>> cars() {
        logger.info("Fetching car list");
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("BWM"));
        cars.add(new Car("GM"));
        cars.add(new Car("Tesla"));
        cars.add(new Car("Mercedes"));

        return CompletableFuture.completedFuture(cars).thenApplyAsync(carList -> carList, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS));
    }

    public static CompletableFuture<Integer> rating(String carId) {
        logger.info("Fetching rating for car " + carId);
        return CompletableFuture.completedFuture(carId.length()).thenApplyAsync(s -> s, CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS));
    }

    public static void main(String[] args) {
        demoAllOf();
        demoListCompletableFuture();
    }

    private static void demoListCompletableFuture() {
        // Hint: we have called join() twice
        // the first join make sure follow "thenAccept" have input
        // the second join make sure leading "thenAccept" is executed.
        cars().thenApply(cars -> cars.stream().map(car -> rating(car.manufacturerId).thenApply(rating -> {
            car.setRating(rating);
            return car;
        })).collect(ListCompletableFutureCollector.toCfList()))
                .join()
                .thenAccept(obj -> System.out.println(obj.toString()))
                .join();
    }

    /**
     * This method demo usage of allOf
     * it is not best solution. demoListCompletableFuture is better solution
     */
    private static void demoAllOf() {
        cars().thenCompose(cars -> {
            List<CompletableFuture<Car>> updatedCars = cars.stream()
                    .map(car -> rating(car.manufacturerId).thenApply(rating -> {
                        car.setRating(rating);
                        return car;
                    })).collect(Collectors.toList());

            return CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[0]))
                    .thenApply(v -> updatedCars.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();
    }

}

class Car {
    final String manufacturerId;
    Integer rating;

    public Car(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    void setRating(Integer rating) {
        this.rating = rating;
    }

    public String toString() {
        return String.format("Brand %s rating %s:", manufacturerId, rating);
    }

}
