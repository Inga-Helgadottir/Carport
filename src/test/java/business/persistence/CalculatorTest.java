package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.MaterialCalculator;
import business.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {
    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "Timmy2008";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";
    private static Database database;
    private static MaterialCalculator materialCalculator;
    private static MaterialFacade materialFacade;


    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            materialCalculator = new MaterialCalculator(database);
            materialFacade = new MaterialFacade(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }


    @Test
    void calcStolpe() throws UserException {
        Material post = materialFacade.SelectMaterialByCategory("post", 3000);
        post.setQuantity(10);
        assertEquals(post.getQuantity(), materialCalculator.calcPost(7800,6000,0,0).getQuantity());
    }


    @Test
    void calcStern() throws UserException {
        Material rafter = materialFacade.SelectMaterialByCategory("rafter", 6000);
        rafter.setQuantity(15);
        assertEquals(rafter.getQuantity(),materialCalculator.calcRafters(6000, 7800).getQuantity());
    }

    @Test
    void calcRem() throws UserException{
        Material rem = materialFacade.SelectMaterialByCategory("rem",7800);
        rem.setQuantity(2);
        assertEquals(rem.getQuantity(), materialCalculator.calcRem(7800,6000).getQuantity());
    }

    @Test
    void calcRoof() throws UserException{
        Material roof = materialFacade.SelectMaterialByCategory("tag",6000);
        roof.setQuantity(6);
        assertEquals(roof.getQuantity(),materialCalculator.calcRoof(7800,6000).getQuantity());
    }



}
