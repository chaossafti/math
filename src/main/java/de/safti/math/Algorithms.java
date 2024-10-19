package de.safti.math;

@SuppressWarnings("unused")
public class Algorithms {

    // default to binet's formula
    public static long fibonacci(int n) {
        return Fibonacci.INSTANCE.binetFormula(n);
    }
}
