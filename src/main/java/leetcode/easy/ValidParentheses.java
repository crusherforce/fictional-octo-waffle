package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ValidParentheses {
    public boolean isValid(String s) {
        List<Character> stack = new ArrayList<>();
        for(int i=0; i<s.length(); i++) {
            if(!stack.isEmpty()) {
                if (match(stack.get(stack.size()-1),s.charAt(i))) {
                    stack.remove(stack.size()-1);
                } else {
                    stack.add(s.charAt(i));
                }
            } else {
                stack.add(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    private boolean match(char a, char b) {
        return (a=='(' && b==')') || (a=='{' && b=='}') || (a=='[' && b==']');
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        System.out.println(v.isValid("()"));
        System.out.println(v.isValid("()[]{}"));
        System.out.println(v.isValid("(]"));
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValid("{[]}"));
    }
}
