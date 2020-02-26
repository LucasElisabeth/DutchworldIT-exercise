import com.orientechnologies.orient.core.exception.ODatabaseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        File file = new File("src/main/resources/data.csv");
        OrientDBWriter writer = new OrientDBWriter(file, "remote:localhost");

        start(writer);
    }

    private static void start(OrientDBWriter writer) {

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Which database would you like to connect to?");
            String name = scanner.nextLine();
            writer.connectToDB(name);
            showOptions();
            menu(scanner, writer, name);

        } catch (ODatabaseException ex) {
            System.err.println("Was not able to connect to this DB, exiting the program.");
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void showOptions() {

        System.out.println("Menu Options:");
        System.out.println("1. Add local file");
        System.out.println("2. Remove all data");
        System.out.println("3. Show records");
        System.out.println("4. Add a new record");
        System.out.println("5. Exit the program\n");
        System.out.print("Please select an option from 1-5\r\n");
    }

    private static void menu(Scanner scanner, OrientDBWriter writer, String name) throws InterruptedException {
        int input = scanner.nextInt();

        while (input != 5) {
            System.out.println("You have entered " + input + "\r\n");
            switch (input) {
                case 1:
                    System.out.println("Writing data from file to DB");
                    writer.writeToDB(writer.getDb());
                    break;
                case 2:
                    System.out.println("Removing all data from " + name + "!");
                    writer.removeFromDB(writer.getDb());
                    break;
                case 3:
                    System.out.println("Showing all current records:");
                    writer.showRecords(writer.getDb());
                    break;
                case 4:
                    System.out.println("Manually adding a record!");
                    writer.addNewRecord(writer.getDb());
                    break;
                default:
                    System.out.println("You have entered an invalid selection!");
                    break;

            }
            Thread.sleep(1000);
            showOptions();
            input = scanner.nextInt();
        }
        System.out.println("You have quit the program\r\n");
        writer.closeConnection(writer.getDb(), writer.getOrientDB());
        System.exit(1);
    }
}
