import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.io.File;
import java.util.ArrayList;

public class OrientDBWriter {

    private String url;
    private File csvFile;
    private CSVReader reader = new CSVReader();
    private OrientDB orientDB;
    private ODatabaseSession db;
    private static final String OPERATION_COMPLETE_MESSAGE = "\n- - - - - - - - - - - -\n| Operation completed |\n- - - - - - - - - - - -";


    public OrientDBWriter(File file, String url) {
        csvFile = file;
        this.url = url;
    }

    public void connectToDB(String DBName) {

        orientDB = new OrientDB(url, OrientDBConfig.defaultConfig());
        db = orientDB.open(DBName, "admin", "admin");
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

            doc.field("name", strings[0]);
            doc.field("workplace", strings[1]);
            doc.field("date", strings[2]);
            doc.field("hometown", strings[3]);
            doc.field("age", strings[4]);
            doc.field("description", strings[5]);

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
