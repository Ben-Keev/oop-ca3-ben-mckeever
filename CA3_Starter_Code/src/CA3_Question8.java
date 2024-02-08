import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question8 {
    
    static Stack<Character> operators = new Stack<>();
    static Stack<Integer> numbers = new Stack<>();

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation");
        equation = in.nextLine().trim();
        evaluateEquation(equation);
    }

    // Read equations
    public static void evaluateEquation(String equation) {
        char[] iterate = equation.toCharArray();

        for (Character c : iterate) {
            // Check operators presence first.
            if((operators.peek() == 'x' || operators.peek() == '/')) {
                // The operator on top has a higher presence.
                evaluateTop(c);
            }

            if (Character.isDigit(c)) { // Number
                numbers.add((int) c);
            } else{ // Operator
                operators.add(c);
            }
        }
    }

    public static int evaluateTop(char operator) {
        switch (operator) {
            case 'x':
                return product(numbers.pop(), numbers.pop());
            
            case '/':
                return quotient(numbers.pop(), numbers.pop());

            case '+':
                return sum(numbers.pop(), numbers.pop());
            case '-':
                return difference(numbers.pop(), numbers.pop());

            default:
                return -1;
        }
    }

    // The order of arguments reflects the order which elements will be popped.
    // Function to calculate the sum of two numbers
    public static int sum(int a, int b) {
        return b + a;
    }

    // Function to calculate the product of two numbers
    public static int product(int a, int b) {
        return b * a;
    }

    // Function to calculate the difference between two numbers
    public static int difference(int a, int b) {
        return b - a;
    }

    // Function to calculate the quotient of two numbers
    public static int quotient(int a, int b) {
        return b / a;
    }
}
