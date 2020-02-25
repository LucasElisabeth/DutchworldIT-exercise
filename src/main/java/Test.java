import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Test {

    public static void main(String[] args) {

        OrientDB orientDB = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orientDB.open("people", "admin", "admin");


        ODocument animal = new ODocument("potato");
        animal.field("name", "Gaudi");
        animal.field("location", "Madrid");
        animal.save();

        db.close();
        orientDB.close();
    }
}
