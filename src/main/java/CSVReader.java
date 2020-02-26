import java.io.*;
import java.util.ArrayList;

public class CSVReader {

    private String line;
    private String selector;

    public CSVReader(String selector) {
        this.selector = selector;
    }

    public CSVReader() {
        selector = ",";
    }

    public ArrayList<String> readFile(File file) {

        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                strings.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }

    public String[] translateLine(String string) {

        return string.split(selector);
    }
}
