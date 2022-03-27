import com.common.entity.Tables;
import com.common.entity.tables.pojos.NewTable;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

import static com.common.entity.Tables.NEW_TABLE;


public class ApplicationTest {

    @Autowired
    private DSLContext dslContext;

    @Test
    public static void main(String[] args) {
        DSLContext dslContext = null;
        try (Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/adam_project", "root", "12345678")) {
            dslContext = DSL.using(conn, SQLDialect.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }        Optional<NewTable> newTable = dslContext.selectFrom(NEW_TABLE)
                .where(NEW_TABLE.ID.greaterOrEqual(1))
                .fetchOptionalInto(NewTable.class);
        System.out.println(newTable.get());
    }
}
