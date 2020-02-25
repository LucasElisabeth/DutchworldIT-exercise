import java.io.File;

public class App {

    public static void main(String[] args) {

        File file = new File("src/main/resources/data.csv");
        OrientDBWriter writer = new OrientDBWriter(file,"remote:localhost");

        writer.writeToDB("people");

    }
}
