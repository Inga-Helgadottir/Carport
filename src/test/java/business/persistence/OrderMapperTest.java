import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "sih";
    private final static String PASSWORD = "mysqlPasswordSIH995";
    private final static String URL = "jdbc:mysql://localhost:3306/fog?" + TESTDATABASE + "serverTimezone=CET&useSSL=false";

    private static Database database;
    private static OrderMapper orderMapper;
    Order o = new Order(24998, 1, 1, 1);

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            orderMapper = new OrderMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }
    @BeforeEach
    public void setUp() {

        // reset test database
        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists query" );
            stmt.execute("create table " + TESTDATABASE + ".query LIKE " + DATABASE + ".users;" );
            stmt.execute(
                    "insert into query values " +
                            "('24998', '1', '1')");
            stmt.execute("drop table if exists `order`" );
            stmt.execute("create table " + TESTDATABASE + ".`order` LIKE " + DATABASE + ".users;" );
            stmt.execute(
                    "insert into query values " +
                            "('24998', '1', '1')");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    void createQuery() throws SQLException, UserException {
        orderMapper.createQuery(o);
    }
}
