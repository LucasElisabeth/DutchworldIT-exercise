import java.io.File;

public class App {

    public static void main(String[] args) {

        File file = new File("data.csv");
        OrientDBWriter writer = new OrientDBWriter(file,"remote:localhost");

        writer.writeToDB("People");
    }
}
