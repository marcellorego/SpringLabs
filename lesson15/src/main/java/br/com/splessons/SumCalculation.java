package br.com.splessons;

import java.util.concurrent.Callable;

public class SumCalculation implements Callable<Integer> {

    final Integer a;
    final Integer b;

    public SumCalculation(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer call() {
        return a + b;
    }
}
