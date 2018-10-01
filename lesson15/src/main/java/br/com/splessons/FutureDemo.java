package br.com.splessons;

import java.util.concurrent.*;

public class FutureDemo {

    private final ExecutorService executorService;

    public FutureDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public String invoke(String value, boolean cancel) {

        String str = "Canceled for value " + value;

        Future<String> future = executorService.submit(() -> {
            // Task
            Thread.sleep(10000l);
            return "Future Demo for value " + value;
        });

        if (cancel) {
            future.cancel(false);
        }

        try {
            str = future.get(20, TimeUnit.SECONDS);
        } catch (CancellationException e) {
            System.err.println("Future Demo Canceled for value " + value);
        } catch (InterruptedException | ExecutionException | TimeoutException e1 ) {
            e1.printStackTrace();
        }

        if (future.isDone() && !future.isCancelled()) {
            try {
                str = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return str;

    }

}