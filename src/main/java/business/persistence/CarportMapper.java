package business.persistence;

import business.entities.Carport;
import business.entities.Query;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarportMapper {
    private Database database;


    public CarportMapper(Database database) {
        this.database = database;
    }

    public Carport createCarport(Carport carport) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `carport` (`length`, `width`, height, `roof_angle`, shed_length, shed_width,`name`, type, price, info) VALUES (?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, carport.getLength());
                ps.setInt(2, carport.getWidth());
                ps.setInt(3, carport.getHeight());
                ps.setInt(4, carport.getRoof_angle());
                ps.setInt(5, carport.getShed_length());
                ps.setInt(6, carport.getShed_width());
                ps.setString(7, carport.getName());
                ps.setString(8, carport.getType());
                ps.setDouble(9, carport.getPrice());
                ps.setString(10, carport.getInfo());

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int carport_id = ids.getInt(1);
                carport.setId(carport_id);
                return carport;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
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
                    Carport carport = new Carport(length, width, height, roof_angle, shed_length, shed_width, name, type, price, info);
                    carport.setId(id);
                    carport.setQuantity(1);
                    carportList.add(carport);
                }
                return carportList;
            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public Carport getEnkeltCarport(int carportID) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport WHERE `id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
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
                    Carport carport = new Carport(length, width, height, roof_angle, shed_length, shed_width, name, type, price, info);
                    carport.setId(carportID);
                    carport.setQuantity(1);
                    return carport;
                } else {
                    throw new UserException("bla");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("database error");
        }
    }


    public Carport getCarportByQuery(Query query) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT carport_id FROM `link_carport_query` WHERE `query_id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, query.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int carport_id = rs.getInt("carport_id");
                    Carport carport = getEnkeltCarport(carport_id);
                    return carport;
                } else {
                    throw new UserException("could not find carport_id from link_carport_query with that query_id");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
