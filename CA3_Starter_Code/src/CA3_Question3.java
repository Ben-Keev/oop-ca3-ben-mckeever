import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 *  Name:
 *  Class Group:
 */

public class CA3_Question3
{
    public static void readFile(String fileName)
    {   
        //https://www.w3schools.com/java/java_files_read.asp
        try {
            File src = new File(fileName);

            Scanner unfiltered = new Scanner(src);
            Scanner in = new Scanner(src);
            in.useDelimiter("[^A-Za-z0-9_]+");

            ArrayList<String> identifiers = new ArrayList<>();
            Map<Integer, String> indexMap = new HashMap<>();

            while(in.hasNext()) identifiers.add(in.next());

            String currentLine;
            int lineNo = 1;

            // https://stackoverflow.com/a/12418360
            while(unfiltered.hasNext()) {
                currentLine = unfiltered.nextLine();
                for (String identifier : identifiers) {
                    if (currentLine.contains(identifier)) {
                        indexMap.put(lineNo, identifier);
                    }
                }
                
                ++lineNo;
            }

            for(Integer key : indexMap.keySet()) {
                System.out.println(key + " " + indexMap.get(key));
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(System.getProperty("user.dir"));
        readFile("CA3_Starter_Code/src/CA3_Question1.java");
    }
}
