import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.ArrayList;

public class CSVReader {

    private HashMap<String, ArrayList<String>> contents;

    /*
    // test if a file can be loaded
    public static void main(String[] args) throws Exception {
        try {
            if (args.length != 1) throw new Exception();
        }
        catch (Exception e) {
            System.out.println("No input file specified, program will terminate. Please rerun the program with the input file path.");
            System.out.println();
            System.out.println("To read the file \"TestInput.csv\", run the command: ");
            System.out.println("java CSVReader TestInput.csv");
            System.exit(0);
        }

        new CSVReader(args[0]);
    }
    */

    // use this to read a simple csv file into a hashmap
    public static HashMap<String, ArrayList<String>> ReadToHashMap(String filename)
    {
        CSVReader readFile = new CSVReader(filename);
        return readFile.getContents();
    }
        
    public CSVReader(String inputFilename) {

        // create contents table
        contents = new HashMap<String, ArrayList<String>>();

        // open file and scanner to read file
        File input = new File("../Resources/" + inputFilename);

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(input);
        }
        catch (FileNotFoundException e) {
            System.out.println("The input file could not be found, program will terminate. Please ensure that the file is added to the Resources directory.");
            System.out.println("The expected path for the input file was: " + input.getAbsolutePath());
            System.exit(0);
        }

        //System.out.println("File successfully loaded!");
        
        // check that titles can be read from the file
        if (!fileReader.hasNextLine()) {
            System.out.println("The input file did not contain any content and could not be loaded.");
            System.exit(0);
        }
        
        // split the first line into tokens, and create lists for each
        String titleLine = fileReader.nextLine();
        StringTokenizer titleTokens = new StringTokenizer(titleLine, ",");
        String[] columnTitles = new String[titleTokens.countTokens()];

        int keyIndex = 0;
        while (titleTokens.hasMoreTokens()) {
            String currentKey = titleTokens.nextToken();
            contents.put(currentKey, new ArrayList<String>());
            columnTitles[keyIndex] = currentKey;
            keyIndex++;
        }

        // go through the rows of the file and add items to their corresponding lists
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            // iterate through all items in a row and add to lists
            for (int i = 0; i < columnTitles.length; i++) {
                // cutting the item out of the string
                int nextComma = line.indexOf(',');
                String item = null;

                // System.out.println(line);

                if (nextComma >= 0) {
                    item = line.substring(0, nextComma);
                    line = line.substring(nextComma + 1);
                }
                // special case for the last item in the line
                else if (nextComma == -1 && line.length() != 0) {
                    item = line.substring(0);
                    line = line.substring(line.length());
                }
                
                // check that the item is not an blank string
                if (item != null && item.trim().isEmpty()) {
                    item = null;
                }

                // if not blank, add to the corresponding list
                if (item != null) {
                    contents.get(columnTitles[i]).add(item);
                }
            }
        }
    }

    private HashMap<String, ArrayList<String>> getContents() {
        return contents;
    }
}
