package leetcode.easy;

import java.util.HashMap;

public class RomanToInteger {
    /*
        Symbol  Value
        I        1
        V        5
        X        10
        L        50
        C        100
        D        500
        M        1000
    */

    public int romanToInt(String s) {
        HashMap<Character, Integer> mapping = new HashMap<>();
        mapping.put('I',1);
        mapping.put('V',5);
        mapping.put('X',10);
        mapping.put('L',50);
        mapping.put('C',100);
        mapping.put('D',500);
        mapping.put('M',1000);

        int retVal = 0;
        int oldVal = 0;
        int newVal = 0;
        for (char c: s.toCharArray()) {
            oldVal = newVal;
            newVal = mapping.get(c);
            if (oldVal < newVal) {
                retVal -= oldVal;
            } else {
                retVal += oldVal;
            }
        }
        retVal += newVal;

        return retVal;
    }

    public void checkAndPrint(String input, int output) {
        System.out.println(romanToInt(input) == output);
    }

    public static void main(String[] args) {
        RomanToInteger r = new RomanToInteger();
        r.checkAndPrint("III",3);
        r.checkAndPrint("IV",4);
        r.checkAndPrint("IX",9);
        r.checkAndPrint("LVIII",58);
        r.checkAndPrint("MCMXCIV",1994);
    }
}
