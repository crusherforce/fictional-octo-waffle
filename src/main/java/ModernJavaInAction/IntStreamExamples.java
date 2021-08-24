package ModernJavaInAction;

import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class IntStreamExamples {

    public static void execute() {
        IntStreamExamples i = new IntStreamExamples();        
        i.sum();
        i.count();
        i.average();
        i.findAny();
        i.doubleStream();
        i.ofInt();
        i.ofInts();
        i.range();
    }

    private List<Integer> list;

    private IntStreamExamples() {
        list = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
    }

    private void sum() {
        int sum = list
            .stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum = " + sum);
    }

    private void count() {
        long count = list
            .stream()
            .mapToInt(Integer::intValue)
            .count();
        System.out.println("Count = " + count);
    }

    private void average() {
        double avg = list
            .stream()
            .mapToInt(Integer::intValue)
            .average()
            .getAsDouble();
        System.out.println("Average = " + avg);
    }

    private void doubleStream() {
        list
            .stream()
            .mapToInt(Integer::intValue)
            .asDoubleStream()
            .forEach(d -> System.out.println(d));
    }

    private void findAny() {
        int anyInt = list
            .stream()
            .mapToInt(Integer::intValue)
            .findAny()
            .getAsInt();
        System.out.println("Any Int = " + anyInt);
    }

    private void ofInt() {
        IntStream.of(100)
            .forEach(i -> System.out.println(i));
    }

    private void ofInts() {
        IntStream.of(100, 200, 300)
            .forEach(i -> System.out.println(i));
    }

    private void range() {
        IntStream a = 
            IntStream.range(1, 10);

        IntStream b = 
            IntStream.rangeClosed(10, 20);

        IntStream c =
            IntStream.concat(a, b);
        
        String p = c
            .boxed()
            .map(Object::toString)
            .collect(Collectors.joining(", "));

        System.out.println(c);
    }
}