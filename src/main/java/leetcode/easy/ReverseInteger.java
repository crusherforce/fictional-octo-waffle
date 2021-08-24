package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ReverseInteger {

    /*
        Examples:
        =========
        123 -> 321
        -123 -> -321
        120 -> 21
        0 -> 0

        Algorithm:
        =========
        1. Memorize and normalize the sign.
        2. Keep doing modulo 10 unless reached 0 and keep a stack of digits.
        3. Take digits from stack and keep multiplying by 10x. Increment x after each multiplication.
        4. Add the result to the return value.

        Learning:
        =========
        1. Catching the overflow. There was a condition that if there is an overflow the function should return 0. The trick is to use a bigger buffer (long in this case) and check for ranges for integer.

        Time:
        =========
        Start:  Nov 20, 2020: 01:05 PM
        End:    Nov 20, 2020: 01:40 PM
        Taken:  35 minutes
     */

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

    public static void print(int x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        ReverseInteger e = new ReverseInteger();
        //print(e.reverse(123));
        //print(e.reverse(-123));
        //print(e.reverse(120));
        //print(e.reverse(0));
        //print(e.reverse(2147483647));
        //print(e.reverse(1534236469));
        print(e.reverse(-2147483648));
    }
}
