import de.safti.math.Fibonacci;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

public class AlgorithmsTest {


    private static String splitLong(long l) {
        return reverse(String.join("_", reverse(Long.toString(l)).split("(?<=\\G.{3})")));
    }

    private static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static void benchmark(String name, Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long timeNanos = System.nanoTime() - start;
        if(timeNanos < 1_000_000) {
            System.out.println(name + " took: " + splitLong(timeNanos) + " ns");
        }
        else {
            System.out.println(name + " took: " + ((double) timeNanos / 1_000_000) + " ms");
        }
    }

    private static String getCallerMethodName() {
        return StackWalker.
                getInstance().
                walk(stream -> stream.skip(2).findFirst().orElseThrow()).
                getMethodName();
    }

    @Test
    public void testFibonacciRecursive() {
        testFibonacci(Fibonacci.Formula.RECURSIVE_CACHED);
    }

    @Test
    public void testFibonacciBinetFormula() {
        testFibonacci(Fibonacci.Formula.BINET_FORMULA);
    }

    @Test
    public void testFibonacciRoundingComputation() {
        testFibonacci(Fibonacci.Formula.ROUNDING_COMPUTATION);
    }

    @Testable
    public void testFibonacci(Fibonacci.Formula formula) {
        benchmark(getCallerMethodName(), () -> {
            Assertions.assertEquals(0, formula.calculate(0));
            Assertions.assertEquals(1, formula.calculate(1));
            Assertions.assertEquals(6765, formula.calculate(20));
            Assertions.assertEquals(1, formula.calculate(-1));


            // assure that range difference is less than n
            long fib68 = 72723460248141L;
            long calculated = formula.calculate(68);
            long diff = Math.abs(calculated - fib68);
            System.out.println(diff);
            Assertions.assertTrue(diff < 68);
        });
    }


}
