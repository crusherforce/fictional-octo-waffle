package skiena;

/**
 * A general purpose runner class
 */

public class App {
    public static void main(String[] args) {
        Sorter.generateData();
        Sorter.sort();
        System.out.println(Sorter.test());
    }
}
