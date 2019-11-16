package charlie.feng.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureAllOf {
    public static final Logger logger = LoggerFactory.getLogger(CompletableFutureAllOf.class);

    public static CompletableFuture<List<Car>> cars() {
        logger.info("Fetching car list");
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("BWM"));
        cars.add(new Car("Mercedes"));

        return CompletableFuture.completedFuture(cars).thenApplyAsync(carList -> carList, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS));
    }

    public static CompletableFuture<Integer> rating(String carId) {
        logger.info("Fetching rating for car " + carId);
        return CompletableFuture.completedFuture(carId.length()).thenApplyAsync(s-> s, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS) );
    }

    public static void main(String[] args) {
        cars().thenCompose(cars -> {
            List<CompletableFuture<Car>> updatedCars = cars.stream()
                    .map(car -> rating(car.manufacturerId).thenApply(rating -> {
                        car.setRating(rating);
                        return car;
                    })).collect(Collectors.toList());

            CompletableFuture done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));

            CompletableFuture<List<Car>> carList = done.thenApply(v -> {
                return updatedCars.stream().map(CompletableFuture::join).collect(Collectors.toList());
            });
            return carList;
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
    String manufacturerId;
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
