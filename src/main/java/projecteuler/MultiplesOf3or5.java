package projecteuler;

public class MultiplesOf3or5 {
    /*
    https://projecteuler.net/problem=1
     */
    private static int sum (int number) {
        int i=0, sum=0;
        while (i<=number) {
            if (i%3==0 || i%5==0) {
                System.out.println(i);
                sum+=i;
            }
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("sum=" + sum(10));
    }
}
