import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import java.sql.*;
import static org.jooq.impl.DSL.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkToDBWithJooq {
    private static final Logger logger = LoggerFactory.getLogger(WorkToDBWithJooq.class);
    private static DSLContext create;
    private static Connection connection;

    public void connectDB(){
        try{
            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/H2DB", "sa", "");
            create = DSL.using(connection);
            logger.info("Connection to DB success!");
        }catch(SQLException e) {
            logger.error("Connection to BD error!", e);
        }
    }

    public void connectionClose(){
        try {
            connection.close();
            logger.info("Connection closed to DB success!");
        } catch (SQLException e) {
            logger.error("Connection closing to BD error!", e);
        }
    }

    public void createTables(){
        try{
            create.dropTableIfExists("USERS").execute();
            create.dropTableIfExists("CONTACTS").execute();

            create.createTable("USERS")
                    .column("ID", SQLDataType.INTEGER.nullable(false))
                    .column("NAME", SQLDataType.VARCHAR.length(50))
                    .column("CONTACT", SQLDataType.INTEGER)
                    .execute();
            logger.info("Table USERS created success!");

            create.createTable("CONTACTS")
                    .column("ID", SQLDataType.INTEGER.nullable(false))
                    .column("PHONE", SQLDataType.VARCHAR.length(50))
                    .execute();
            logger.info("Table CONTACTS created success!");

            create.alterTable("CONTACTS")
                    .add(constraint("PK_CONTACTS_ID").primaryKey("ID"))
                    .execute();
            logger.info("Constraint PRIMARY KEY in the table CONTACTS of the column ID created success!");

            create.alterTable("USERS")
                    .add(constraint("PK_USERS_ID").primaryKey("ID"))
                    .execute();
            logger.info("Constraint PRIMARY KEY in the table USERS of the column ID created success!");

            create.alterTable("USERS")
                    .add(constraint("FK_ID").foreignKey("CONTACT").references("CONTACTS", "ID"))
                    .execute();
            logger.info("Constraint FOREIGN KEY in the table USERS of the column CONTACT created success!");

            create.insertInto(DSL.table("contacts"), DSL.field("ID"), DSL.field("PHONE"))
                    .values(1, "+70000000001")
                    .values(2, "+70000000002")
                    .values(3, "+70000000003")
                    .values(4, "+70000000004")
                    .execute();
            logger.info("Insert into data in the table CONTACTS success!");

            create.insertInto(DSL.table("users"), DSL.field("ID"), DSL.field("NAME"), DSL.field("CONTACT"))
                    .values(1, "Vasy", 1)
                    .values(2, "Pety", 2)
                    .values(3, "Masha", 3)
                    .values(4, "Mad Max", null)
                    .execute();
            logger.info("Insert into data in the table USERS success!");
        }catch (Exception e){
            logger.error("Creating table or constraint error!", e);
        }
    }

    public void queries(){
        try{
            Result<?> query1 = create.select(DSL.field("NAME"), DSL.field("PHONE"))
                    .from(DSL.table("users"))
                    .innerJoin(DSL.table("contacts"))
                    .on(DSL.field("CONTACTS.ID").eq(DSL.field("USERS.CONTACT")))
                    .fetch();
            logger.info("Executed query success!", query1);

            int paramsID = 1;
            String paramsName = "Pety";
            Result<?> query2 = create.select(DSL.field("NAME"), DSL.field("PHONE"))
                    .from(DSL.table("users"))
                    .innerJoin(DSL.table("contacts"))
                    .on(DSL.field("CONTACTS.ID").eq(DSL.field("USERS.CONTACT")))
                    .where(DSL.field("USERS.ID").greaterThan(paramsID))
                    .and(DSL.field("USERS.NAME").eq(paramsName))
                    .fetch();
            logger.info("Executed query success!", query2);
        }catch (Exception e){
            logger.error("Executing query error!", e);
        }
    }
}
