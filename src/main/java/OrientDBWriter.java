import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OrientDBWriter {

    private String url;
    private SimpleDateFormat format;
    private File csvFile;
    private CSVReader reader = new CSVReader();
    private OrientDB orientDB;
    private ODatabaseSession db;
    private static final String OPERATION_COMPLETE_MESSAGE = "\n- - - - - - - - - - - -\n| Operation completed |\n- - - - - - - - - - - -";


    public OrientDBWriter(File file, String url) {
        csvFile = file;
        this.url = url;
        format = new SimpleDateFormat("dd-MM-yyyy");
    }

    public void connectToDB(String DBName) {

        orientDB = new OrientDB(url, OrientDBConfig.defaultConfig());
        db = orientDB.open(DBName, "admin", "admin");
        db.createClassIfNotExist("Person");
    }

    public void closeConnection(ODatabaseSession db, OrientDB orientDB) {

        db.close();
        orientDB.close();
    }

    public void writeToDB(ODatabaseSession db) {

        ArrayList<String> stringArrayList = reader.readFile(csvFile);
        for (String string : stringArrayList) {

            String[] strings = reader.translateLine(string);
            ODocument doc = new ODocument("Person");

            try {
                doc.field("date", format.parse(strings[2]));
                doc.field("age", Double.parseDouble(strings[4]));
                doc.field("name", strings[0]);
                doc.field("workplace", strings[1]);
                doc.field("hometown", strings[3]);
                doc.field("description", strings[5]);

            } catch (ParseException ex) {
                System.err.println("The date format is incorrect. Please use \"dd/MM/yyyy\"");
                continue;
            } catch (NumberFormatException ex) {
                System.err.println("This is not a proper age. Please use numbers.");
                continue;
            }
            db.save(doc);
        }

        System.out.println(OPERATION_COMPLETE_MESSAGE);
    }


    public void removeFromDB(ODatabaseSession db) {

        String query = "DELETE from person";
        db.command(query);

        System.out.println(OPERATION_COMPLETE_MESSAGE);
    }

    public void showRecords(ODatabaseSession db) {

        String query = "SELECT * from person";
        OResultSet rs = db.query(query);

        if (!rs.hasNext()) {
            System.out.println("[-] There are currently no records to show.");
        }

        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.println("[-] person: " + item.getProperty("name"));
        }

        rs.close();

        System.out.println(OPERATION_COMPLETE_MESSAGE);
    }

    public void addNewRecord(ODatabaseSession db, Scanner scanner) {

        try {

            ODocument doc = new ODocument("Person");

            System.out.println("date:");
            doc.field("date", format.parse(scanner.nextLine()));
            System.out.println("Age:");
            doc.field("age:", Double.parseDouble(scanner.nextLine()));
            System.out.println("Name:");
            doc.field("name", scanner.nextLine());
            System.out.println("workplace:");
            doc.field("workplace", scanner.nextLine());
            System.out.println("hometown:");
            doc.field("hometown", scanner.nextLine());
            System.out.println("description:");
            doc.field("description:", scanner.nextLine());

            db.save(doc);

        } catch (ParseException ex) {
            System.err.println("The date format is incorrect. Please use \"dd/MM/yyyy\"");
            closeConnection(db, orientDB);
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.err.println("This is not a proper age. Please use numbers.");
            closeConnection(db, orientDB);
            System.exit(1);
        }
    }

    public String getUrl() {
        return url;
    }

    public OrientDB getOrientDB() {
        return orientDB;
    }

    public ODatabaseSession getDb() {
        return db;
    }
}
