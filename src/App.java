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
        generateCustomer("customer.json", args);
    }

    public static String ListToString(ArrayList<String> list) {
        String result = "";
        for (String item : list) {
            result += item + ", ";
        }
        result = result.substring(0, result.length() - 2); // remove trailing comma and space
        return result; 
    }

    public static void generateCustomer (String outputFilename, String[] args) {
        // load in general csv file
        HashMap<String, ArrayList<String>> fileContents = CSVReader.ReadToHashMap(args[0]);

        // get items from original csv file
        String firstName = fileContents.get("firstName").get((int) (Math.random() * fileContents.get("firstName").size()));
        String lastName = fileContents.get("lastName").get((int) (Math.random() * fileContents.get("lastName").size()));
        String occupationTitle = fileContents.get("occupationTitle").get((int) (Math.random() * fileContents.get("occupationTitle").size()));
        String roomType = fileContents.get("roomType").get((int) (Math.random() * fileContents.get("roomType").size()));
        String budget = fileContents.get("budget").get((int) (Math.random() * fileContents.get("budget").size()));
        String difficulty = fileContents.get("difficulty").get((int) (Math.random() * fileContents.get("difficulty").size()));
        ArrayList<String> colors = new ArrayList<String>();
        for (int i = 0; i < (int) (Math.random() * 3 + 1); i++) {
            int indexToAdd = (int) (Math.random() * fileContents.get("colors").size());
            boolean added = false;
            do {
                String colorToAdd = fileContents.get("colors").get(indexToAdd);
                if(!colors.contains(colorToAdd)) {
                    colors.add(colorToAdd);
                    added = true;
                }
                else {
                    indexToAdd = (indexToAdd + 1) % fileContents.get("colors").size();
                }
            } while (!added);
        }

        // fetch and load room specific csv file
        HashMap<String, ArrayList<String>> roomContents = CSVReader.ReadToHashMap(roomType + ".csv");

        // get items from room specific csv file
        ArrayList<String> furniture = new ArrayList<String>();
        for (int i = 0; i < (int) (Math.random() * 3 + 2); i++) {
            int indexToAdd = (int) (Math.random() * roomContents.get("furniture").size());
            boolean added = false;
            do {
                String furnitureToAdd = roomContents.get("furniture").get(indexToAdd);
                if(!furniture.contains(furnitureToAdd)) {
                    furniture.add(furnitureToAdd);
                    added = true;
                }
                else {
                    indexToAdd = (indexToAdd + 1) % roomContents.get("furniture").size();
                }
            } while (!added);
        }
        ArrayList<String> limitations = new ArrayList<String>();
        for (int i = 0; i < (int) (Math.random() * 2 + 1); i++) {
            int indexToAdd = (int) (Math.random() * roomContents.get("limitations").size());
            boolean added = false;
            do {
                String limitationToAdd = roomContents.get("limitations").get(indexToAdd);
                if(!limitations.contains(limitationToAdd)) {
                    limitations.add(limitationToAdd);
                    added = true;
                }
                else {
                    indexToAdd = (indexToAdd + 1) % roomContents.get("limitations").size();
                }
            } while (!added);
        }

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(occupationTitle);
        System.out.println(roomType);
        System.out.println(budget);
        System.out.println(difficulty);
        for (int i = 0; i < colors.size(); i++) {
            System.out.print(colors.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < furniture.size(); i++) {
            System.out.print(furniture.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < limitations.size(); i++) {
            System.out.print(limitations.get(i) + " ");
        }
        System.out.println();

    }
}
