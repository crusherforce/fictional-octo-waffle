package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class addToArrayForm {

    public static List<Integer> addToArrayForm(int[] A, int K) {
        int digitsOfK = (int) (1 + (K == 0 ? 0 : Math.log10(K)));
        int[] k = new int[digitsOfK];
        int i = digitsOfK - 1;
        while (K > 0) {
            k[i--] = K % 10;
            K /= 10;
        }
        int m = digitsOfK - 1, n = A.length - 1;
        int carry = 0;
        Stack<Integer> retVal = new Stack<>();
        while (m >= 0 && n >= 0) {
            int val = k[m--] + A[n--] + carry;
            carry = val / 10;
            retVal.push(val % 10);
        }

        while (m >= 0) {
            int val = k[m--] + carry;
            carry = val / 10;
            retVal.push(val % 10);
        }

        while (n >= 0) {
            int val = A[n--] + carry;
            carry = val / 10;
            retVal.push(val % 10);
        }

        if (carry > 0) {
            retVal.push(carry);
        }

        List<Integer> l = new ArrayList<>();

        while (!retVal.isEmpty()) {
            l.add(retVal.pop());
        }

        return l;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 7, 4};
        List<Integer> l = addToArrayForm(array, 181);
        System.out.println(l);
    }
}

