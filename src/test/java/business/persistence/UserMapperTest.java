package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "sih";
    private final static String PASSWORD = "mysqlPasswordSIH995";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;

    //har lavet en test db som hedder fog_test, som har de samme tabeller og værdier som fog

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }

    @Test
    public void testLogin01() throws UserException {
        // Can we log in
        User user = userMapper.login( "tim", "123");
        assertTrue( user != null );
    }

    @Test
    public void testLogin02() throws UserException {
        // We should get an exception if we use the wrong password
        assertThrows(UserException.class, () ->
            {User user = userMapper.login( "tim", "555"); });
    }

    @Test
    public void testLogin03() throws UserException {
        // Tim is supposed to be a customer
        User user = userMapper.login( "tim", "123" );
        assertEquals( "customer", user.getRole() );
    }

    @Test
    public void testCreateUser01() throws UserException {
        String name = "Carl";
        String email = "carl@gmail.com";
        String password1 = "pass";
        String password2 = "pass";
        int telephone = 54685798;
        int zipcode = 3000;
        String city = "Helsingor";
        String address = "Frejasvej 7";

        if (password1.equals(password2)) {
            User tmp = new User(email, password2, "customer");
            tmp.setName(name);
            tmp.setTelephone(telephone);

            tmp.setZipcode(zipcode);
            tmp.setCity(city);
            tmp.setAddress(address);

            int zipcodeId = userMapper.createUserCheckZipcode(tmp);
            int cityId = userMapper.createUserCheckCity(tmp);

            if (zipcodeId == 0) {
                zipcodeId = userMapper.createUserZipcode(tmp);
            }

            tmp.setZipcodeId(zipcodeId);

            if (cityId == 0) {
                cityId = userMapper.createUserCity(tmp);
            }

            tmp.setCityId(cityId);

            int addressId = userMapper.createUserAddress(tmp);
            tmp.setAddress_id(addressId);

            User user = userMapper.createUser(tmp);

            //check if the user has the correct data
            assertEquals("Carl", user.getName());
            assertEquals("carl@gmail.com", user.getEmail());
            assertEquals("pass", user.getPassword());
            assertEquals(54685798, user.getTelephone());
            assertEquals(3000, user.getZipcode());
            assertEquals("Frejasvej 7", user.getAddress());
            assertEquals("Helsingor", user.getCity());
            assertEquals(zipcodeId, user.getZipcodeId());
            assertEquals(cityId, user.getCityId());
            assertEquals(addressId, user.getAddress_id());
        }
    }
}
