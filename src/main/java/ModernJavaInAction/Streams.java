package ModernJavaInAction;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Streams {
    public enum Flags {
        basicExample,
        wordExample,
        numberExample        
    }

    public static void execute() {
        Flags flag = Flags.numberExample;

        switch(flag) {
            case basicExample:
                basicExample();
                break;
            case wordExample:
                wordExample();
                break;
            case numberExample:
                numberExample();
                break;
        }
    }

    public static String delay(String value) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            //ignore;
        }
        return value;
    }

    public static void wordExample() {
        List<String> result =
            Arrays.asList("Donald Trump".split(" "))
                .stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public static void numberExample() {
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        list1
            .stream()
            .flatMap(i -> 
                list2
                .stream()
                .map(j -> {
                        return new int[] {i,j};
                    }
                )
            )
            .filter(a -> (a[0]+a[1])%3==0)
            .forEach(a -> System.out.println("("+a[0]+","+a[1]+")"));
    }

    public static void basicExample() {
        // List<Object> players = 
        Arrays.asList(
            new Player("Ronaldo", Country.Portugal, 1996),
            new Player("Rooney", Country.England, 1998),
            new Player("Messi", Country.Argentina, 1997),
            new Player("Crespo", Country.Argentina, 1980),
            new Player("Maradona", Country.Argentina, 1976))
        .stream()
        .parallel()
        .filter(player -> player.country() == Country.Argentina)
        .skip(1)
        // .dropWhile(player -> player.startOfPlay() >= 1980)
        .map(Player::name)
        .map(Streams::delay)
        .forEachOrdered(System.out::println);
        // .collect(Collectors.toList());

        // System.out.println(players);
    }
}