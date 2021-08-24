package spoj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NDIV {

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int count = 0;
        int a = s.nextInt();
        int b = s.nextInt();
        int n = s.nextInt();

        for (int i=a; i<=b; i++) {
            if (n == countDivisors(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static int countDivisors(int a) {
        List<Integer> factors = new ArrayList<>();
        int div = a/2;

        while (div>0) {
            if (a%div==0) {
                factors.add(div);
            }
            div--;
        }

        return factors.size();
    }
}
