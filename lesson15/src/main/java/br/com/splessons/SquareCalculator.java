package br.com.splessons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {
     
    private final ExecutorService executor;

    public SquareCalculator(ExecutorService executor) {
        this.executor = executor;
    }

    private Integer square(Integer input) throws InterruptedException {
        Thread.sleep(5000);
        return Integer.valueOf(input * input);
    }

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> square(input));
    }
}