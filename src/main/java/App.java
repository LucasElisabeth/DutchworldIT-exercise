import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;

import java.io.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        File file = new File("src/main/resources/data.csv");
        OrientDBWriter writer = new OrientDBWriter(file, "remote:localhost");

        start(writer, file);
    }

    private static void start(OrientDBWriter writer, File file) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which database would you like to connect to?");
        String name = scanner.nextLine();
        writer.connectToDB(name);
        System.out.println("You have connected to " + name + "!\n");

        System.out.println("Menu Options:");
        System.out.println("1. Add data");
        System.out.println("2. Some menu option");
        System.out.println("3. Some menu option");
        System.out.println("4. Some menu option");
        System.out.println("5. Exit the program\n");
        System.out.print("Please select an option from 1-5\r\n");


        int input = Integer.parseInt("2");

        if (input <= 0 || input > 5) {
            System.out.println("You have entered an invalid selection, stopping program!\r\n");
            System.exit(0);
        } else if (input == 5) {
            System.out.println("You have quit the program\r\n");
            System.exit(1);
        } else {
            System.out.println("You have entered " + input + "\r\n");
            switch (input) {
                case 1:

            }
        }
    }
}
