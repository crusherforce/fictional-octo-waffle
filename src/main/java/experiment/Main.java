package experiment;

public class Main {
    public static void main(String[] args) {
        JavaUtilArrays j = new JavaUtilArrays(new int[]{5, 12, 5, 4, 33, 44});
        j.sort().printAll();

        System.out.println(Math.abs(Integer.MIN_VALUE));
    }
}
