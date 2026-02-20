package karn.core.collections;

import java.util.List;
import java.util.stream.Collectors;

public class Teeing {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        var result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue), // Collector 1: Sum
                        Collectors.averagingDouble(Integer::doubleValue), // Collector 2: Average
                        (sum, avg) -> "Sum = " + sum + ", Average = " + avg // Merge results
                ));

        System.out.println(result);
    }
}
