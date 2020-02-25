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

    public OrientDBWriter(File file, String url) {
        csvFile = file;
        this.url = url;
    }

    public void writeToDB(String DBName) {

        OrientDB orientDB = new OrientDB(url, OrientDBConfig.defaultConfig());

        ODatabaseSession db = orientDB.open(DBName, "admin", "admin");

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


}
