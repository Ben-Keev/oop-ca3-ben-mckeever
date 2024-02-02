import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question4_copy {

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException
    {
        File file = new File("CA3_Starter_Code/" + filename);
        Scanner in = new Scanner(file);

        Stack<String> tags = new Stack<>();

        while(!in.hasNext()) {
            tags.push(in.next());
        }

        ArrayList<String> openingTags = new ArrayList<>();
        ArrayList<String> closingTags = new ArrayList<>();

        while(!tags.isEmpty()) {
            
        }

        return false;
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
