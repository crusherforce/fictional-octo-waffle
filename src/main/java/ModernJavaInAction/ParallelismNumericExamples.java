package ModernJavaInAction;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ParallelismNumericExamples {
    public static void execute() {
        Random r = new Random();
        int length = 20;
        List<Integer> listOfInts = new ArrayList<>();
        for (int i=0; i<length; i++)
            listOfInts.add(r.nextInt(20));
        
        System.out.println("List of Integers : ");
        print(listOfInts);

        Comparator<Integer> comparator = Integer::compare;

        System.out.println("List of Integers (Sorted) : ");
        listOfInts.sort(comparator);            
        print(listOfInts);

        System.out.println("List of Integers (Reverse Sorted) : ");
        listOfInts.sort(comparator.reversed());            
        print(listOfInts);

        int sum = 
            listOfInts
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum : " + sum);
    }

    private static void print(List<Integer> listOfInts) {
        String printString = 
            listOfInts
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(printString);
    }
}