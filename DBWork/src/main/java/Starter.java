public class Starter {

    public static void main(String[] args) {
        WorkToDBWithJooq db = new WorkToDBWithJooq();
        db.connectDB();
        db.createTables();
        db.queries();
        db.connectionClose();
    }
}
