package experiment;

import java.util.Arrays;

public class JavaUtilArrays {
    private int[] mIntArray;
    private String[] mStringArray;

    JavaUtilArrays(int[] intArray) {
        mIntArray = intArray;
    }

    JavaUtilArrays(String[] stringArray) {
        mStringArray = stringArray;
    }

    JavaUtilArrays printAll() {
        if (mIntArray != null) {
            for (int a :
                    mIntArray) {
                System.out.println(a);
            }
        } else if (mStringArray != null) {
            for (String s :
                    mStringArray) {
                System.out.println(s);
            }
        }
        return this;
    }

    JavaUtilArrays sort() {
        if (mIntArray != null) {
            Arrays.sort(mIntArray);
        } else if (mStringArray != null) {
            Arrays.sort(mStringArray);
        }
        return this;
    }
}
