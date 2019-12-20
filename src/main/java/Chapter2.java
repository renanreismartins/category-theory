import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class Chapter2 {

    public static <A, B> Function<A, B> memoize(Function<A, B> f) {
        Map<A, B> cache = new HashMap<>();

        return a -> {
            if (!cache.containsKey(a)) {
                cache.put(a, f.apply(a));
            }
            return cache.get(a);
        };
    }

    public static void main(String[] args) {
        Function<Integer, String> impureLongRunningFunction = a -> {
            sleep();
            System.out.println("long running function");
            return String.valueOf(a);
        };

        Function<Integer, String> memoizedImpureLongFunction = memoize(impureLongRunningFunction);
        System.out.println(memoizedImpureLongFunction.apply(1));
        System.out.println(memoizedImpureLongFunction.apply(1));



        Function<Integer, Random> seedF = seed -> {
            System.out.println("you called the random generator");
            return new Random(seed);
        };
        Function<Integer, Random> memoizedSeedF = memoize(seedF);
        System.out.println(memoizedSeedF.apply(3).nextInt());
        System.out.println(memoizedSeedF.apply(3).nextInt());
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}