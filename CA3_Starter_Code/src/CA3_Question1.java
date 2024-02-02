import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question1
{
  static Scanner keyboard = new Scanner(System.in);
  static Stack<Integer> lot = new Stack<Integer>();

  public static void runSimulation()
  {
    int input = -1;

    lot.add(1);
    lot.add(2);
    lot.add(3);
    lot.add(4);
    lot.add(5);


    while (input != 0) {
      System.out.println("Write operation: ");
      input = keyboard.nextInt();
      if(input < 0) {
        retrieveCar(input * -1);
      } else {
        lot.push(input);
      }
    }
  }

  public static void retrieveCar(int targetCar) {
    var tempMovement = new ArrayList<Integer>();
    Integer[] lotArray = lot.toArray(new Integer[0]);
    // Typecast toArray Object[] to Integer[] https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/#:~:text=arr%20%3D%20al.toArray(arr)%3B

    for (int i = lotArray.length -1; i > -1; i--) { // Iterate array backwards
      if (lotArray[i] != targetCar) {
        System.out.println("Temporarily Moving Car " + lotArray[i]);
        tempMovement.add(lot.pop());
      } else {
        System.out.println("Retrieving Car " + lotArray[i]);
        lot.pop();
        break;
      }
    }

    for (int i = tempMovement.size() -1; i > -1; i--) { // Iterate array backwards
      System.out.println("Restoring Car" + tempMovement.get(i));
      lot.add(tempMovement.get(i));    
    }
  }

  public static void main(String[] args) {
      runSimulation();
  }
}
