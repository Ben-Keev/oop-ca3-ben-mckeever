import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {WEST,SOUTH,EAST, NORTH};

public class CA3_Question9
{

    static int[] location = {4, 3};
    static Stack<Path> paths = new Stack<>();

    // Vertical movements and horizontal movements easily distinguished
    // With this, we can easily turn to the opposite direction
    //https://stackoverflow.com/questions/2760995/arraylist-initialization-equivalent-to-array-initialization
    static ArrayList<DIRECTION> vertical = new ArrayList<>(Arrays.asList(DIRECTION.NORTH, DIRECTION.SOUTH));
    static ArrayList<DIRECTION> horizontal = new ArrayList<>(Arrays.asList(DIRECTION.WEST, DIRECTION.EAST));


    // A multi dimensional array
    static char[][] image = {
        //    0    1    2    3    4    5    6    7
            {'■', '■', '■', '■', '■', '■', '■', '■'}, // 0
            {'■', 'O', 'O', 'O', 'O', 'O', 'O', '■'}, // 1
            {'■', '■', '■', '■', 'O', '■', '■', '■'}, // 2
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', '■'}, // 3
            {'■', '■', '■', '■', 'O', '■', '■', '■'}, // 4
            {'■', 'O', 'O', 'O', 'O', '■', '■', '■'}, // 5
            {'■', '■', '■', '■', 'O', '■', '■', '■'}, // 6
            {'■', '■', '■', '■', '■', '■', '■', '■'}, // 7
    }; 

    public static void main(String[] args) {
        display();
        solve();
    }

    public static int[] traverse(DIRECTION dir) {
        int[] newLocation = Arrays.copyOf(location, location.length);
        
        switch (dir) {
            case NORTH:
                newLocation[1] -= 1;
                return newLocation;
            case SOUTH:
                newLocation[1] += 1;
                return newLocation;
            case EAST: 
                newLocation[0] += 1;
                return newLocation; 
            case WEST:
                newLocation[0] -= 1;
                return newLocation;
            
            default:

                return new int[] {0, 0};
        }
    }

    public static char getChar(int[] coords) {
        if(isLocationInBounds(coords)) { // Prevent out of bounds
            return image[coords[1]][coords[0]];
        } else {
            return '■';
        }
    }
    
    // Ignore currently headed direction and its opposite
    public static boolean isIntersection(DIRECTION dir) {
        if(vertical.contains(dir)) { // Ignore vertical paths
            return getChar(traverse(DIRECTION.WEST)) == 'O' || getChar(traverse(DIRECTION.EAST))  == 'O';
        } else if(horizontal.contains(dir)) { // Ignore vertical paths
            return getChar(traverse(DIRECTION.NORTH)) == 'O' || getChar(traverse(DIRECTION.SOUTH)) == 'O';
        } else {
            return false;
        }
    }

    public static void pushPossiblePaths() {
        for (DIRECTION direction : DIRECTION.values()) {
            if(getChar(traverse(direction)) == 'O') {
                paths.add(new Path(location, direction));
            }
        }
    }

    // Will ignore the axes of an inputted direction
    public static void pushPossiblePaths(DIRECTION dir) {
        if(vertical.contains(dir)) { // Ignore vertical paths
            if (isIntersection(dir)) {
                for(DIRECTION direction : horizontal) {
                    if(getChar(traverse(direction)) != '■')
                    paths.add(new Path(location, direction));
                    System.out.println("Added " + new Path(location, direction).toString());
                }
            }
        } else if(horizontal.contains(dir)) { // Ignore vertical paths
            if (isIntersection(dir)) {
                for(DIRECTION direction : vertical) {
                    if(getChar(traverse(direction)) != '■')
                    paths.add(new Path(location, direction));
                    System.out.println("Added " + new Path(location, direction).toString());
                }
            }
        }
    }

    public static void display()
    {
       
            for (int y = 0; y < image.length; y++)
            { 
                for (int x = 0; x < image[0].length; x++)
                {
                if(x == location[0] && y == location[1]) { // Player's point
                    System.out.printf("%4s", 'X');
                } else { // Player is not there
                    System.out.printf("%4s", image[y][x]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isLocationInBounds(int[] location) {
        return location[0] > -1 && location[0] < image.length && location[1] > -1 && location[1] < image[0].length;
    }

    public static void solve()
    {
        boolean found = false;
        Path currentPath;

        pushPossiblePaths();
        while (isLocationInBounds(location) && !paths.isEmpty()) { // While within the bounds of the array AND The stack is not empty
            System.out.println(paths.toString());
            currentPath = paths.pop();
            location = currentPath.coordinates;

            while( getChar(traverse(currentPath.dir)) != '■') { // Found a dead end
                location = traverse(currentPath.dir);
                System.out.println(Arrays.toString(location));
                display();
                if (isIntersection(currentPath.dir)) { // After moving, check if intersection
                    System.out.println("Found intersection");
                    pushPossiblePaths(currentPath.dir);
                }
            }

            for (DIRECTION direction : DIRECTION.values()) {
                if (!isLocationInBounds(traverse(direction))) {
                    System.out.println("Congratulations! Found the exit.");
                    break;
                }
            }
        }
    }
}

class Path {
    public int[] coordinates;
    public DIRECTION dir;

    public Path(int[] coordinates, DIRECTION dir) {
        this.coordinates = coordinates;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Path [coordinates=" + Arrays.toString(coordinates) + ", dir=" + dir + "]";
    }
}