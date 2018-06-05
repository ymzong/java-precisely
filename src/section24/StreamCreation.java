package section24;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class includes examples that demonstrate the creation of Stream and its performance advantage.
 */
public class StreamCreation {

    static void basicFiniteStreams() {
        // explicit element enumeration
        Stream<String> languages = Stream.of("Basic", "Pascal", "C++", "C", "Python", "Java");
        Stream<String> emptyStream = Stream.empty();
        print(languages);

        // there are primitive-type specialized Streams too
        IntStream firstPrimes = IntStream.of(2, 3, 5, 7, 11);
        print(firstPrimes);

        // conversion from Array
        String[] pets = { "Unicorn", "Gnar", "Tibbers" };
        Stream<String> petStream = Arrays.stream(pets);
        print(petStream);

        // conversion from Collection
        Set<String> country = Collections.singleton("USA");
        Stream<String> countryStream = country.stream();
        print(countryStream);
    }

    public static void main(String[] argv) {
        basicFiniteStreams();
    }

    // helper methods that prints out all elements of a Stream in order
    private static void print(Stream<?> stream) {
        stream.sequential().forEach(s -> System.out.print(s + "\t"));
        System.out.println();
    }

    private static void print(IntStream stream) {
        stream.sequential().forEach(i -> System.out.print(i + "\t"));
        System.out.println();
    }
}
