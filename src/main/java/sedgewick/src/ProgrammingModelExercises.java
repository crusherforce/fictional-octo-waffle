package sedgewick.src;

public class ProgrammingModelExercises {
    /*
    1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array
    a[][] such that a[i][j] is true if i and j are relatively prime (have no common factors),
    and false otherwise.
     */
    private static boolean[][] createBoolArray(int n) {
        boolean[][] array = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = IsMutuallyPrime(i + 1, j + 1);
            }
        }
        return array;
    }

    private static void printBoolArray(boolean[][] array) {
        for (boolean[] booleans : array) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(booleans[j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    private static int gcd(int big, int small) {
        if (big % small == 0) {
            return small;
        } else {
            return gcd(small, big % small);
        }
    }

    private static boolean IsMutuallyPrime(int i, int j) {
        boolean retVal;
        if (i == j) {
            retVal = false;
        } else if (i > j) {
            retVal = (j != 1 && gcd(i, j) == 1);
        } else {
            retVal = (i != 1 && gcd(j, i) == 1);
        }
        return retVal;
    }

    public static void main(String[] a) {
        // test_1_1_30();
        test_1_1_32();
    }

    private static void test_1_1_32() {
        double[][] b = new double[0][0];
        Matrix.printAll(b);
        System.out.println();
        Matrix.printAll(Matrix.transpose(b));
    }

    private static void test_1_1_30() {
        printBoolArray(createBoolArray(10));
    }
}
