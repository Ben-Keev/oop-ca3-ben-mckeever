import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question10
{
    static HashMap<String, HashSet<DistanceTo>> map = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException{
        readFile();
        System.out.println(findPaths("Pendleton"));
    }

    public static void readFile() throws FileNotFoundException {
        File file = new File("map.txt");
        Scanner in = new Scanner(file);

        String city1, city2;
        int distance;

        while(in.hasNext()) {
            city1 = in.next();
            city2 = in.next();
            distance = in.nextInt();

            addCities(city1, city2, distance);
        }
    }

    public static HashMap<String, Integer> findPaths(String from) {
        PriorityQueue<DistanceTo> distanceQueue = new PriorityQueue<>();
        distanceQueue.add(new DistanceTo(from, 0));

        HashMap<String, Integer> shortestKnownDistance = new HashMap<>();

        do { // Do while, so that it runs the first time while dQueue is empty
            DistanceTo smallestElement = distanceQueue.poll();
            String target = smallestElement.getTarget();

            if(!shortestKnownDistance.containsKey(target)) {
                int d = smallestElement.getDistance();
                shortestKnownDistance.put(target, d);
                map.get(target).forEach((c) -> 
                    distanceQueue.add(new DistanceTo(c.getTarget(), d + c.getDistance()))
                );
            }
        } while (!distanceQueue.isEmpty());

        return shortestKnownDistance;
    }

    // Show what each city is linked to
    public static void printLinks(String city) {
        if(map.containsKey(city))
        map.get(city).forEach((n) -> System.out.println(n.toString()));
    }

    public static void addCities(String city1, String city2, int distance) {
        DistanceTo distanceToObj = new DistanceTo(city2, distance);
        HashSet<DistanceTo> setObj = new HashSet<>();

        if(map.containsKey(city1)) {
            map.get(city1).add(distanceToObj);
        } else {
            setObj.add(distanceToObj);
            map.put(city1, setObj);
        }

        distanceToObj = new DistanceTo(city1, distance);
        setObj = new HashSet<>();

        if(map.containsKey(city2)) {
            map.get(city2).add(distanceToObj);
        } else {
            setObj.add(distanceToObj);
            map.put(city2, setObj);
        }
    }
}

class DistanceTo implements Comparable<DistanceTo> {
    private String target;  
    private int distance;  
    
    public DistanceTo(String city, int dist) {  
        target = city;
        distance = dist; 
    }
    
    public String getTarget() { return target; }  
    public int getDistance() { return distance; }  

    @Override
    public int compareTo(DistanceTo o) {
        return distance - o.distance;
    }
}