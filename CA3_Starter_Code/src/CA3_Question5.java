import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *  Name:
 *  Class Group:
 */

public class CA3_Question5
{

    static PriorityQueue takeoff = new PriorityQueue<>();
    static PriorityQueue land = new PriorityQueue<>();

    public static void takeoff(String flightSymbol) {
        takeoff.add(flightSymbol);
    }

    public static void land(String flightSymbol) {
        land.add(flightSymbol);
    }

    public static void next() {
        if(land.isEmpty()) {
            takeoff.poll();
        } else {
            land.poll();
        }
    }

    public static void quit() {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        takeoff("Flight-100");
        takeoff("Flight-220");
        land("Flight-320");
        next();
        next();
        next();
        quit();
    }
}
