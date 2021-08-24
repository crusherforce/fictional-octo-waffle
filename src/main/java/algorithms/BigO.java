package algorithms;

public class BigO {


    public void foo(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // O(n)
        }

        for (int i = 0; i < array.length; i++) {
            // O(n)
        }

    }

    public void bar(int[] array1, int[] array2) {

        for (int i = 0; i < array1.length; i++) {
            // ...
            for (int j = 0; j < array2.length; j++) {
                // O (n*m)
            }
        }

    }

    public void baz(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            for (int j = 0; j < array.length; j++) {
                // ...
                if (array[i] < array[j]) {
                    // O(n*n)
                }
            }
        }

    }

    public void beep(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            for (int j = 0; j < array.length; j++) {
                // ...
                for (int k = 0; k < 9999999; k++) {
                    // Jump!
                }
            }
        }

    }

}