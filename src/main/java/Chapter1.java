import java.util.function.Function;

public class Chapter1 {

    public static <A> Function<A, A> identity() {
        return a -> a;
    }

    public static <A, B, C> Function<A, C> compose(Function<A, B> f, Function<B, C> g) {
        return x -> g.apply(f.apply(x));
    }

    public static void main(String[] args) {
        Function<Double, Double> f = x -> Double.valueOf(x / 2 );
        Function<Double, Integer> g = x -> x.intValue();

        System.out.println(f.apply(3.0));
        System.out.println(g.apply(3.3));


        System.out.println("Composition");
        System.out.println(compose(f, g).apply(5.0));

        System.out.println("Composition respecting identity");
        System.out.println(compose(f, Chapter1.identity()).apply(5.0));

    }
}