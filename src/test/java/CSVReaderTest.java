import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReaderTest {

    public static void main(String[] args) {

        String line = "";

        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data.csv"))) {

            while ((line = br.readLine()) != null) {

                strings.add(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder("");

        for (String string : strings) {
            stringBuilder.append(string);
            stringBuilder.append(".\n");
        }
        System.out.println(stringBuilder.toString());

        System.out.println("\n\nComplete!");
    }
}
