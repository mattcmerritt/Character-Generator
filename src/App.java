import java.util.HashMap;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            if (args.length != 2) throw new Exception();
        }
        catch (Exception e) {
            System.out.println("No input file specified, program will terminate. Please rerun the program with the input file path and the output file name.");
            System.out.println();
            System.out.println("To read the file \"TestInput.csv\" and save to \"Customer.json\", run the command: ");
            System.out.println("java App TestInput Customer");
            System.exit(0);
        }
        System.out.println("--------------------------------------------");
        generateCustomer("customer.json", args);
        System.out.println("--------------------------------------------");
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
        HashMap<String, ArrayList<String>> fileContents = CSVReader.ReadToHashMap(args[0] + ".csv");

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

        System.out.print("Your randomly generated customer is ");
        System.out.print(firstName + " " + lastName + ", ");
        System.out.print("who is a " + occupationTitle);
        System.out.println(" requesting that you design a " + roomType + " for them. ");
        System.out.print("Their budget is $" + budget + ", ");
        System.out.println("and this job was rated with a difficulty of " + difficulty + ".");
        System.out.println("In terms of colors, they would like the room to contain: ");
        for (int i = 0; i < colors.size(); i++) {
            System.out.println("\t- " + colors.get(i));
        }
        System.out.println("They have also requested that you include the following pieces of furniture in your design: ");
        for (int i = 0; i < furniture.size(); i++) {
            System.out.println("\t- " + furniture.get(i));
        }
        System.out.println("Finally, the limitations of the job site include: ");
        for (int i = 0; i < limitations.size(); i++) {
            System.out.println("\t- " + limitations.get(i));
        }
        
        // write to json file
        FileWriter jsonOutput = new FileWriter(args[1]);
        jsonOutput.writeString("firstName", firstName);
        jsonOutput.writeString("lastName", lastName);
        jsonOutput.writeString("occupationTitle", occupationTitle);
        jsonOutput.writeString("roomType", roomType);
        jsonOutput.writeString("budget", budget);
        jsonOutput.writeString("difficulty", difficulty);
        jsonOutput.writeList("colors", colors);
        jsonOutput.writeList("furniture", furniture);
        jsonOutput.writeList("limitations", limitations);
        jsonOutput.saveFile();

        System.out.println("The raw data for this customer has been saved in \"" + args[1] + ".json\", so feel free to take a look if you need to review these later.");
    }
}
