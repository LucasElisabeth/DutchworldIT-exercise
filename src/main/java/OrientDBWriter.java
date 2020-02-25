import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.record.impl.ODocument;

import java.io.File;
import java.util.ArrayList;

public class OrientDBWriter {

    private String url;
    private File csvFile;
    private CSVReader reader = new CSVReader();
    private OrientDB orientDB;
    private ODatabaseSession db;


    public OrientDBWriter(File file, String url) {
        csvFile = file;
        this.url = url;
    }

    public void connectToDB(String DBName) {

        orientDB = new OrientDB(url, OrientDBConfig.defaultConfig());
        db = orientDB.open(DBName, "admin", "admin");
    }

    public void writeToDB(ODatabaseSession db, OrientDB orientDB) {

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

        db.close();
        orientDB.close();
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
