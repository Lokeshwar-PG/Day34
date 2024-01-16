import org.example.JDBCCRUD;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class JDBCCRUDTest {
    JDBCCRUD jdbc = new JDBCCRUD();

    @Test
    public void updateTest() throws SQLException {
        double result = jdbc.updateSalary("Rajesh", 80000);
        Assert.assertEquals(result, 80000.00, 0);
    }

}
