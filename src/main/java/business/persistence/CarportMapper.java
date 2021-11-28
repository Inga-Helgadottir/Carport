package business.persistence;

import business.entities.Carport;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarportMapper {
    private Database database;


    public CarportMapper(Database database) {
        this.database = database;
    }


    public List<Carport> getEnkeltcarporte() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `carport`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<Carport> carportList = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int roof_angle = rs.getInt("roof_angle");
                    int shed_length = rs.getInt("shed_length");
                    int shed_width = rs.getInt("shed_width");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    double price = rs.getDouble("price");
                    String info = rs.getString("info");
                    Carport carport = new Carport(length,width,height,roof_angle,shed_length,shed_width,name,type,price,info);
                    carport.setId(id);
                    carportList.add(carport);
                }
                return carportList;
            }
            catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
