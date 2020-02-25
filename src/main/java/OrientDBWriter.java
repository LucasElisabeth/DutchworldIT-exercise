import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;

import java.io.File;

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


        db.close();
        orientDB.close();
    }


}
