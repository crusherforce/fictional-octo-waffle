package leetcode.easy;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        int i=0, h_i=0;
        while (i<needle.length() && h_i<haystack.length()) {
            if (needle.charAt(i) == haystack.charAt(h_i)) {
                i++;
            } else {
                h_i-=i;
                i=0;
            }
            h_i++;
        }
        return i == needle.length() ? (h_i-needle.length()) : -1;
    }

    public static void main(String[] args) {
        String haystack,needle;
        ImplementStrStr i = new ImplementStrStr();

        haystack="hello";needle="ll";
        System.out.println(i.strStr(haystack, needle));

        haystack="mississippi";needle="issip";
        System.out.println(i.strStr(haystack, needle));
    }
}
