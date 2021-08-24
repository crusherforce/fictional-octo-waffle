package skiena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorter {
    private static Random myRandom = new Random(101);
    private static List<Integer> myList = new ArrayList<>();;

    public static void generateData() {
        int numberOfItems = myRandom.nextInt()%20;
        for (int i=0; i<numberOfItems; i++) {
            myList.add(myRandom.nextInt()%100);
        }
    }

    public static boolean test() {
        for (int i=0; i<myList.size()-1; i++) {
            if(myList.get(i) > myList.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    public static void sort() {
        for (int i=0; i<myList.size(); i++) {

        }
    }
}
