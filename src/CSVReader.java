import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;

public class CSVReader {

    private HashMap<String, String> contents;

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
    public static HashMap<String, String> ReadToHashMap(String filename)
    {
        CSVReader readFile = new CSVReader(filename);
        return readFile.getContents();
    }
        
    public CSVReader(String inputFilename) {

        // create contents table
        contents = new HashMap<String, String>();

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
        
        // read through file line by line, and add pairs of items to contents
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            StringTokenizer tokens = new StringTokenizer(line, ",");

            // if the line only contains two items, add them as a key-value pair to contents table
            if (tokens.countTokens() == 2) {
                contents.put(tokens.nextToken(), tokens.nextToken());
            }
            // output any rows not saved
            else {
                System.out.println("Row (\"" + line + "\") could not be saved!");
            }
        }
    }

    private HashMap<String, String> getContents() {
        return contents;
    }
}
