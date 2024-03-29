import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question4 {

    

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException
    {
        File file = new File("CA3_Starter_Code/" + filename);
        Scanner in = new Scanner(file);

        Stack<String> openingTags = new Stack<>();
        ArrayList<String> closingTags = new ArrayList<>();

        String current;

        while (in.hasNext()) {
            current = in.next();

            if(!current.contains("/")) {
                openingTags.push(current);                
            } else {
                closingTags.add(current);
            }
        }

        for(String closingTag : closingTags) {
            if(!openingTags.isEmpty() && !(closingTag.substring(2).contains(openingTags.peek()))) {
                // It is not empty and tags match.
                openingTags.pop();
            }
            else {
                return false;
            }
        }

        // All tags have been examined, if openTags is empty the file is valid.
        return openingTags.isEmpty();
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for(String fName: files) {
            System.out.print(fName +": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }
}
