package ModernJavaInAction;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class IntPredicateExamples {

    public static void execute() {
        int[] array = new int[] {1,2,3,4,5,6,7,8,9};

        IntPredicate evenOnly  = (int i) -> i%2 == 0;
        IntPredicate oddOnly   = (int i) -> i%2 != 0;
        IntPredicate primeOnly = (int i) -> {
            if (i <= 1) return false;
            for(int f=2; f<=i/2; f++)
                if (i%f == 0) return false;
            return true;
        };

        // prints all elements
        testIntPredicate(array, evenOnly.or(oddOnly), "evenOnly.or(oddOnly)");
        // prints no element
        testIntPredicate(array, evenOnly.and(oddOnly), "evenOnly.and(oddOnly)");
        // prints prime element
        testIntPredicate(array, primeOnly, "primeOnly");
    }

    public static void testIntPredicate(int[] array, IntPredicate i, String name) {
        System.out.println(name);
        Arrays
            .stream(array)
            .forEach(a -> {
                if (i.test(a)) 
                    System.out.print(a + " ");
                }
            ); 
    }
}