package br.com.splessons;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("\nHello World!");
        Thread th = new Thread(r);
        th.start();


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Hello World II!");
                } catch (InterruptedException e) {
                    System.err.print(e);
                }
            }
        };
        Thread th1 = new Thread(r1);
        th1.start();

        System.out.println();
        final List<String> list = Arrays.asList("My", "Name", "is", "Marcello");
        Runnable r2 = () -> {
            Consumer<String> style = (String s) -> System.out.println(s);
            list.forEach(style);
        };

        Thread th2 = new Thread(r2);
        th2.start();

        ExecutorService executor
                = Executors.newSingleThreadExecutor();

        System.out.println();
        FutureDemo futureDemo = new FutureDemo(executor);
        String result = futureDemo.invoke("OK", false);
        System.out.println(result);

        result = futureDemo.invoke("ERROR", true);
        System.out.println(result);

        System.out.println();
        Future<Integer> future = new SquareCalculator(executor).calculate(10);
        while(!future.isDone()) {
            System.out.println("Calculating...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }

        Integer square=0;
        try {
            square = future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("\nSquare = "+square);

        executor.shutdown();


//        SumCalculation sumCalculation = new SumCalculation();
//        Future<Integer> future = sumCalculation.calculate(2, 3);
//
//        Integer result=0;
//        try {
//            result = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("\nSum = "+result);
//        future.cancel(true);


//        SquareCalculator squareCalculator = new SquareCalculator();
//        Future<Integer> result = squareCalculator.calculate(Integer.valueOf(2));
//
//        try {
//            assert result.get().equals(Integer.valueOf(4));
//        } catch (Exception e) {
//            System.err.print(e);
//            System.exit(1);
//        }

    }
}
