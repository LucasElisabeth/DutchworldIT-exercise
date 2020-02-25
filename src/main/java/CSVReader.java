import java.io.*;

public class CSVReader {

    private String line = "";
    private String seperator = ",";

    public String[] readFile(File file) {

        String[] strings = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                strings = line.split(seperator);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
