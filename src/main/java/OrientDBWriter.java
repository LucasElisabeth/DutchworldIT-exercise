import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;

import java.io.File;

public class OrientDBWriter {

    private File csvFile;
    private CSVReader reader = new CSVReader();

    public OrientDBWriter(File file) {
        csvFile = file;
    }

    public static void main(String[] args) {


        OrientDB orientDB = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());


        orientDB.close();
    }


}
