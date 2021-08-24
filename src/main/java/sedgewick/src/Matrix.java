package sedgewick.src;

/*
    1.1.33 Matrix library. Write a library Matrix that implements the following API:
    public class Matrix
    static double dot(double[] x, double[] y) vector dot product
    static double[][] mult(double[][] a, double[][] b) matrix-matrix product
    static double[][] transpose(double[][] a) transpose
    static double[] mult(double[][] a, double[] x) matrix-vector product
    static double[] mult(double[] y, double[][] a) vector-matrix product
    Develop a test client that reads values from standard input and tests all the methods.
*/

public class Matrix {

    public static double dot(double[] x, double[] y) {
        if (x == null || y == null || x.length == 0 || y.length == 0) {
            return 0;
        }
        double val = 0;
        for (double xi : x) {
            for (double yi : y) {
                val += xi * yi;
            }
        }
        return val;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        b = transpose(b);
        double[][] m = new double[a.length][b[0].length];
        int i, j;
        i = j = 0;
        for (double[] ai : a) {
            for (double[] bi : b) {
                m[i][j] = dot(ai, bi);
                i++;
            }
            j++;
        }
        return m;
    }

    public static double[][] transpose(double[][] a) {
        double[][] a_t = (a == null || a.length == 0) ? new double[0][0] : new double[a[0].length][a.length];
        for (int i = 0; i < a_t.length; i++) {
            for (int j = 0; j < a_t[0].length; j++) {
                a_t[i][j] = a[j][i];
            }
        }
        return a_t;
    }

    public static void printAll(double[][] a) {
        for (double[] ai : a) {
            for (double aij : ai) {
                System.out.print(aij);
                System.out.print('\t');
            }
            System.out.println();
        }
    }

    public static double[] multiply(double[][] a, double[] x) {
        double[][] n = new double[x.length][1];
        int i = 0;
        for (double xi : x) {
            n[i++][0] = xi;
        }
        double[][] r = multiply(a, n);
        r = transpose(r);
        return r[0];
    }

    public static double[] multiply(double[] x, double[][] a) {
        double[] r = new double[a[0].length];
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; i < x.length; i++) {
                r[i] += x[j] * a[j][i];
            }
        }
        return r;
    }

}
