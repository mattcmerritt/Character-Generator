import java.util.HashMap;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            if (args.length != 1) throw new Exception();
        }
        catch (Exception e) {
            System.out.println("No input file specified, program will terminate. Please rerun the program with the input file path.");
            System.out.println();
            System.out.println("To read the file \"TestInput.csv\", run the command: ");
            System.out.println("java App TestInput.csv");
            System.exit(0);
        }

        HashMap<String, ArrayList<String>> fileContents = CSVReader.ReadToHashMap(args[0]);
        
        System.out.println("Reading contents from table...");
        for (String key : fileContents.keySet()) {
            System.out.println(key + ":\t" + ListToString(fileContents.get(key)));
        }
    }

    public static String ListToString(ArrayList<String> list) {
        String result = "";
        for (String item : list) {
            result += item + ", ";
        }
        result = result.substring(0, result.length() - 2); // remove trailing comma and space
        return result; 
    }
}
