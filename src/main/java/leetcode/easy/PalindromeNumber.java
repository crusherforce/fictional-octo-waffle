package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // Negative numbers can't be palindrome
        if (x<0) {
            return false;
        }

        int y = reverse(x);
        return x == y;
    }

    public int reverse(int x) {
        List<Integer> stack = new ArrayList<>();
        long retVal = 0;
        boolean isOverflow = false;

        // Step 1
        boolean isSigned = x < 0;
        x = isSigned ? -1 * x : x;

        // Step 2
        while (x != 0) {
            int mod = x % 10;
            stack.add(mod);
            x = x / 10;
        }

        // Step 3-4
        for (Integer integer : stack) {
            retVal *= 10;
            retVal += integer;
        }

        retVal = isSigned ? -1 * retVal : retVal;

        if (retVal > Integer.MAX_VALUE || retVal < Integer.MIN_VALUE) {
            isOverflow = true;
        }

        return isOverflow ? 0 : (int)retVal;
    }

    public void check(int x, boolean y) {
        System.out.println(isPalindrome(x) == y);
    }

    public static void main(String[] args) {
        PalindromeNumber e = new PalindromeNumber();
        e.check(121, true);
        e.check(-121, false);
        e.check(10, false);
        e.check(-101, false);
    }
}
