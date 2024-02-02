import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question2
{
    static Stack<Pair> fillStack = new Stack<Pair>();
    static int count = 1;

    /*
        Starter function to create the 2D array and populate it with 0

     */
    public static int[][]  floodFillStart() {
        int[][] arr = new int[10][10];

        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
       return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    /*
      Choose a row and column
     */
    public static void choosePair() {
        Scanner kb = new Scanner(System.in);
        int row, column;

        System.out.print("Enter row: ");
        row = kb.nextInt();

        System.out.print("Enter column: ");
        column = kb.nextInt();

        fillStack.push(new Pair(row, column));
    }


    private static void fill(int r, int c, int[][] arr)
    {

        if(arr[r][c] == 0)
        {
        arr[r][c] = count;
        ++count;
        
        // Check indexes are within bounds of array and are not already filled

        if(r-1 > -1 && arr[r-1][c] == 0) fillStack.push(new Pair(r-1, c)); // North
        if(r+1 < arr.length && arr[r+1][c] == 0) fillStack.push(new Pair(r+1, c)); // South
        if(c-1 > -1 && arr[r][c-1] == 0) fillStack.push(new Pair(r, c-1)); // East
        if(c+1 < arr.length && arr[r][c+1] == 0) fillStack.push(new Pair(r, c+1)); // West
        }
        
        if(!fillStack.isEmpty()) { // Empty stack by calling function recursively
            Pair next = fillStack.pop();
            fill(next.row, next.column, arr); // Stack overflow error
        }
    }

    public static void start()
    {
        int[][] arr = floodFillStart();
        fillStack.push(new Pair(5, 5));
        Pair next = fillStack.pop();
        fill(next.row, next.column, arr);
        display(arr);
    }

    public static void main(String[] args) {
        start();
    }

}

class Pair {
    public int row;
    public int column;

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Pair [row=" + row + ", column=" + column + "]";
    }
  
}