import java.util.Stack;

class stack {
    public static boolean isBalanced(String s) {
        Stack<Character> chars = new Stack();
        for(char c:s.toCharArray()) {
            if (c == '(' || c == '{') {
                chars.push(c);
            }
            else if (c== ')') {
                if(chars.isEmpty()) {
                    return false;
                }
                else if (c == ')' && chars.peek() == '(') {
                    chars.pop();
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }
}