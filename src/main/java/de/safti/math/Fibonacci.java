package de.safti.math;

import java.util.HashMap;
import java.util.Map;

import static de.safti.math.MathConstants.GOLDEN_RATIO;

public class Fibonacci {
    public static final Fibonacci INSTANCE = new Fibonacci();
    private static final Map<Integer, Long> cache = new HashMap<>();
    private static final double FIVE_ROOT = 2.2360679775;

    private Fibonacci() {
    }

    public long recursiveCached(int n) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        }

        if(n < 1) {
            return n == 0 ? 0L : 1L;
        }

        long l = recursiveCached(n - 1) + recursiveCached(n - 2);
        cache.put(n, l);
        return l;

    }

    public long binetFormula(int n) {
        return (long) Math.ceil((Math.pow(GOLDEN_RATIO, n) - Math.pow((-GOLDEN_RATIO), -n)) / FIVE_ROOT);
    }

    public long roundingComputation(int n) {
        return n > 0 ? Math.round(Math.pow(MathConstants.GOLDEN_RATIO, n) / FIVE_ROOT) : n == 0 ? 0 : 1;
    }

    public interface Formula {
        Formula RECURSIVE_CACHED = Fibonacci.INSTANCE::recursiveCached;
        Formula BINET_FORMULA = Fibonacci.INSTANCE::binetFormula;
        Formula ROUNDING_COMPUTATION = Fibonacci.INSTANCE::roundingComputation;

        long calculate(int n);

    }


}
