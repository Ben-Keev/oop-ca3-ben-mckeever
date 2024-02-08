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

        System.out.println(numbers.peek());
    }

    // Read equations
    public static void evaluateEquation(String equation) {
        char[] iterate = equation.toCharArray();

        for (Character c : iterate) {

            // Let's write it more simply...

            // System.out.println(numbers);
            // System.out.println(operators);
            
            if(Character.isDigit(c)) { // If you read a number
                numbers.add(Character.getNumericValue(c)); // Push it on the number stack
            } else if (c == '(') { // Else if you read a (
                operators.add(c); // Push it on the operator stack
            } else if (c == '+' || c == '-' || c == 'x' || c=='/') {

                if(!operators.isEmpty()) { // Prevent stack is empty error
                    while((c == '+' || c == '-') && (operators.peek() == 'x' || operators.peek() =='/')) {
                        numbers.add(evaluateTop(operators.pop())); // Evaluate the top.
                        if(operators.isEmpty()) break; // Exit loop if stack is empty.
                    }
                }
                    
                operators.add(c); // Push on the operator stack
            } else if (c == ')') {  // If you read a )
                while (operators.peek() != '(') { // While the top is not (
                    numbers.add(evaluateTop(operators.pop())); // Evaluate the top
                }
                operators.pop(); // Pop the '('
            }
        } // There is no more input

        while (!operators.isEmpty()) { // While the operator stack is not empty
            numbers.add(evaluateTop(operators.pop()));
            System.out.println(numbers);
            System.out.println(operators);
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
