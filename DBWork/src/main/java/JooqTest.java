import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import java.sql.*;
import static org.jooq.impl.DSL.*;


public class JooqTest {

    public static void main(String[] args)
        throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./H2DB");
        DSLContext create = DSL.using(conn);
        create.dropTableIfExists("USERS").execute();
        create.dropTableIfExists("CONTACTS").execute();
        create.createTable("USERS")
                .column("ID",SQLDataType.INTEGER.nullable(false))
                .column("NAME",SQLDataType.VARCHAR.length(50))
                .column("CONTACT",SQLDataType.INTEGER.nullable(false))
                .execute();

        create.createTable("CONTACTS")
                .column("ID",SQLDataType.INTEGER.nullable(false))
                .column("PHONE",SQLDataType.VARCHAR.length(50))
                .execute();

        create.alterTable("CONTACTS")
                .add(constraint("PK_CONTACTS_ID").primaryKey("ID"))
                .execute();

        create.alterTable("USERS")
                .add(constraint("PK_USERS_ID").primaryKey("ID"))
                .execute();

        create.alterTable("USERS")
                .add(constraint("FK_ID").foreignKey("CONTACT").references("CONTACTS","ID"))
                .execute();

        create.insertInto(DSL.table("contacts"),DSL.field("ID"),DSL.field("PHONE"))
                .values(1,"+70000000001")
                .values(2,"+70000000002")
                .values(3,"+70000000003").execute();
        create.insertInto(DSL.table("users"),DSL.field("ID"),DSL.field("NAME"),DSL.field("CONTACT"))
                .values(1,"Vasy",1)
                .values(2,"Pety",2)
                .execute();

        Result<?> result = create.select(DSL.field("NAME"),DSL.field("PHONE"))
                .from(DSL.table("users"))
                .innerJoin(DSL.table("contacts"))
                .on(DSL.field("CONTACTS.ID").eq(DSL.field("USERS.CONTACT")))
                .fetch();

        System.out.println(result);
    }
}
