import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import java.sql.*;
import static org.jooq.impl.DSL.*;


public class WorkToDBWithJooq {

    private static DSLContext create;
    private static Connection connection;

    public void connectDB(){
        try{
            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/H2DB", "sa", "");
            create = DSL.using(connection);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectionClose(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

            create.createTable("CONTACTS")
                    .column("ID", SQLDataType.INTEGER.nullable(false))
                    .column("PHONE", SQLDataType.VARCHAR.length(50))
                    .execute();

            create.alterTable("CONTACTS")
                    .add(constraint("PK_CONTACTS_ID").primaryKey("ID"))
                    .execute();

            create.alterTable("USERS")
                    .add(constraint("PK_USERS_ID").primaryKey("ID"))
                    .execute();

            create.alterTable("USERS")
                    .add(constraint("FK_ID").foreignKey("CONTACT").references("CONTACTS", "ID"))
                    .execute();

            create.insertInto(DSL.table("contacts"), DSL.field("ID"), DSL.field("PHONE"))
                    .values(1, "+70000000001")
                    .values(2, "+70000000002")
                    .values(3, "+70000000003")
                    .values(4, "+70000000004")
                    .execute();

            create.insertInto(DSL.table("users"), DSL.field("ID"), DSL.field("NAME"), DSL.field("CONTACT"))
                    .values(1, "Vasy", 1)
                    .values(2, "Pety", 2)
                    .values(3, "Masha", 3)
                    .values(4, "Mad Max", null)
                    .execute();

            System.out.println("Tables success created!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void queries(){
        try{
            Result<?> query1 = create.select(DSL.field("NAME"), DSL.field("PHONE"))
                    .from(DSL.table("users"))
                    .innerJoin(DSL.table("contacts"))
                    .on(DSL.field("CONTACTS.ID").eq(DSL.field("USERS.CONTACT")))
                    .fetch();

            System.out.println(query1);

            int paramsID = 1;
            String paramsName = "Pety";
            Result<?> query2 = create.select(DSL.field("NAME"), DSL.field("PHONE"))
                    .from(DSL.table("users"))
                    .innerJoin(DSL.table("contacts"))
                    .on(DSL.field("CONTACTS.ID").eq(DSL.field("USERS.CONTACT")))
                    .where(DSL.field("USERS.ID").greaterThan(paramsID))
                    .and(DSL.field("USERS.NAME").eq(paramsName))
                    .fetch();

            System.out.println(query2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
