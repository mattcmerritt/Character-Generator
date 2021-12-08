import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriter {
    
    private File outputFile;
    private PrintWriter writer;

    private boolean isFirstLine;

    /*
    // test writing to a json file
    public static void main(String[] args) {
        FileWriter fw = new FileWriter("character");
        fw.writeString("item1", "value1");
        ArrayList<String> l = new ArrayList<String>();
        l.add("value2.1");
        l.add("value2.2");
        fw.writeList("item2", l);
        fw.saveFile();
    }
    */

    public FileWriter(String filename) {
        outputFile = new File("../out/" + filename + ".json");
        try {
            writer = new PrintWriter(outputFile);
        }
        catch (FileNotFoundException e) {
            System.out.println("The output file could not be found, program will terminate. Please ensure that the file is added to the Resources directory.");
            System.out.println("The expected path for the output file was: " + outputFile.getAbsolutePath());
            System.exit(0);
        }

        isFirstLine = true;
        writer.println("{");
    }

    public void saveFile() {
        writer.println(); // end current line
        writer.println("}"); // add comma to previous line
        writer.close();
    }

    public void writeString(String id, String value) {
        if (!isFirstLine) {
            writer.println(","); // add comma to previous line
        }
        else {
            isFirstLine = false;
        }
        writer.print("\t\"" + id + "\": \"" + value + "\"");
    }

    public void writeList(String id, ArrayList<String> list) {
        if (!isFirstLine) {
            writer.println(","); // add comma to previous line
        }
        else {
            isFirstLine = false;
        }
        writer.print("\t\"" + id + "\": [");
        
        for (int i = 0; i < list.size() - 1; i++) {
            writer.print("\"" + list.get(i) + "\", ");
        }
        writer.print("\"" + list.get(list.size() - 1) + "\"]");
    }

}
