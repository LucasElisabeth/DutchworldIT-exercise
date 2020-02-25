import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.exception.ODatabaseException;

import java.io.*;
import java.util.Scanner;

public class App {

    private static final String OPERATION_COMPLETE_MESSAGE = "\n- - - - - - - - - - - -\n| Operation completed |\n- - - - - - - - - - - -";

    public static void main(String[] args) {

        File file = new File("src/main/resources/data.csv");
        OrientDBWriter writer = new OrientDBWriter(file, "remote:localhost");

        start(writer);
    }

    private static void start(OrientDBWriter writer) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which database would you like to connect to?");
        String name = scanner.nextLine();
        try {
            writer.connectToDB(name);
        } catch (ODatabaseException ex) {
            System.err.println("Was not able to connect to this Database, exiting the program.");
            System.exit(1);
        }

        showOptions(name);

        int input = scanner.nextInt();

        if (input <= 0 || input > 5) {
            System.out.println("You have entered an invalid selection, stopping program!\r\n");
            writer.closeConnection(writer.getDb(), writer.getOrientDB());
            System.exit(0);
        } else if (input == 5) {
            System.out.println("You have quit the program\r\n");
            writer.closeConnection(writer.getDb(), writer.getOrientDB());
            System.exit(1);
        } else {
            System.out.println("You have entered " + input + "\r\n");
            switch (input) {
                case 1:
                    System.out.println("Writing data from file to DB");
                    writer.writeToDB(writer.getDb(), writer.getOrientDB());
                    writer.closeConnection(writer.getDb(), writer.getOrientDB());
                    System.out.println(OPERATION_COMPLETE_MESSAGE);
                    return;
                case 2:
                    System.out.println("Removing all data from " + name + "!");
                    writer.closeConnection(writer.getDb(), writer.getOrientDB());
                    return;
                case 3:
                    break;
                case 4:
                    break;
            }
        }
    }

    private static void showOptions(String name) {
        System.out.println("You have connected to " + name + "!\n");

        System.out.println("Menu Options:");
        System.out.println("1. Add data");
        System.out.println("2. Remove all data");
        System.out.println("3. Some menu option");
        System.out.println("4. Some menu option");
        System.out.println("5. Exit the program\n");
        System.out.print("Please select an option from 1-5\r\n");
    }
}
