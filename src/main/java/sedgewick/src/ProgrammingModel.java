package sedgewick.src;

import java.util.Scanner;

class Simple {
    Simple() {
        // A simple class to hold all the methods
    }

    double sqrt(double n) {
        for (double i = 0; i <= n; i += 0.001) {
            if (i * i > n) {
                return i;
            }
        }
        return n;
    }

    double sqrt1(double c) {
        if (c < 0) {
            return Double.NaN;
        }
        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
        }
        return t;
    }
}

public class ProgrammingModel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            double n = sc.nextDouble();
            if (n < 0) {
                break;
            }
            Simple s = new Simple();
            System.out.println(s.sqrt(n));
            System.out.println(s.sqrt1(n));
        }
    }
}
