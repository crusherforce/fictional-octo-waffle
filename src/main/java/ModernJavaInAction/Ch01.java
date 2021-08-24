package ModernJavaInAction;

import java.io.File;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Random;

public class Ch01 {
    public void doFileRead() {
        FileReader f = new FileReader();
        for (File file : f.getFiles()) {
            System.out.println(file.getName());
        }
    }

    public void doCheckApples() {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(160, Apple.Color.RED));
        inventory.add(new Apple(156, Apple.Color.GREEN));
        inventory.add(new Apple(124, Apple.Color.RED));
        inventory.add(new Apple(170, Apple.Color.GREEN));
        inventory.add(new Apple(100, Apple.Color.RED));

        List<Apple> heavyApples =
            inventory
                .parallelStream()
                .filter((Apple a) -> 150<=a.getWeight())
                .collect(Collectors.toList());

        System.out.println("--- Heavy (>=150) Apples ---");
        for(Apple a :heavyApples) {
            System.out.println(a);
        }           
             
        System.out.println("--- Green Apples ---");
        for(Apple a : 
            filterApples(
                inventory, 
                (Apple a) -> Apple.Color.GREEN == a.getColor()
            )
        ) {
            System.out.println(a);
        }

        inventory.sort(
            (Apple a1, Apple a2) 
                -> a1.getWeight().compareTo(a2.getWeight())
        );
        System.out.println("--- Sorted Apples ---");
        for (Apple a : inventory) {
            System.out.println(a);
        }
    }

    private List<Apple> filterApples(
        List<Apple> inventory, 
        Predicate<Apple> p) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple a : inventory) {
            if (p.test(a)) {
                filteredApples.add(a);
            }
        } 
        return filteredApples;
    }

    public void doStreamComparison() {
        int[] testData = CreateRandomIntegerArray(100000);
        List<Integer> testDataList = new ArrayList<>();
        
        for (int t : testData) {
            testDataList.add(t);
        }

        long start = System.currentTimeMillis();
        testDataList
            .parallelStream()
            .forEach(t -> isPrime(t));
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("Total time taken = " + totalTime);
    }

    private int[] CreateRandomIntegerArray(int length) {
        int[] array = new int[length];
        Random randomGen = new Random();
        for (int i=0; i<length; i++) {
           // array[i] = randomGen.nextInt(length);
           array[i] = i;
        }
        return array;
    }

    private boolean isEven(int t) {
        return t%2 == 0;
    }

    private boolean isPrime(int t) {
        for (int i=2; i<=t/2; i++) {
            if (t%i != 0) {
                return false;
            }
        }
        return true;
    }
}