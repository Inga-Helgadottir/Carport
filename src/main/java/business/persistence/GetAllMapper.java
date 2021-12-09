package business.persistence;

import business.entities.GetAll;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetAllMapper {
    private Database database;

    public GetAllMapper(Database database) {
        this.database = database;
    }

    public List<GetAll> GetAll() {
        List<GetAll> getAllList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT o.id, o.status, o.created, o.price AS totalPrice, q.id AS queryId, q.message, u.id, u.name AS username, u.email, u.telephone, l.quantity, c.id AS carportId, c.type, c.price, c.name, c.length, c.width, c.height, c.roof_angle, c.shed_length, c.shed_width " +
                    "FROM `user` AS u " +
                    "INNER JOIN `order` AS o " +
                    "ON u.id = o.user_id " +
                    "INNER JOIN query AS q " +
                    "ON o.query_id = q.id " +
                    "INNER JOIN link_carport_query AS l " +
                    "ON q.id = l.query_id " +
                    "INNER JOIN carport AS c " +
                    "ON l.carport_id = c.id;";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("id");
                    int queryId = rs.getInt("queryId");
                    int carportId = rs.getInt("carportId");
                    String status = rs.getString("status");
                    String type = rs.getString("type");
                    String message = rs.getString("message");
                    String userName = rs.getString("username");
                    String carportName = rs.getString("name");
                    Timestamp created = rs.getTimestamp("created");
                    int price = rs.getInt("price");
                    double totalPrice = rs.getDouble("totalPrice");
                    String userEmail = rs.getString("email");
                    int userPhone = rs.getInt("telephone");
                    int quantity = rs.getInt("quantity");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int roof_angle = rs.getInt("roof_angle");
                    int shed_length = rs.getInt("shed_length");
                    int shed_width = rs.getInt("shed_width");

                    GetAll getAll = new GetAll(status, message, created, totalPrice, userId, userName, userEmail, userPhone, quantity, carportId, type, price, carportName, length, width, height, roof_angle, shed_length, shed_width, queryId);
                    getAllList.add(getAll);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllList;
    }
    public List<GetAll> checkForMessages(int userId) throws UserException {
        List<GetAll> checkForMsgs = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT o.id, o.status, o.created, o.price AS totalPrice, q.id AS queryId, o.message, u.id, u.name AS username, u.email, u.telephone, l.quantity, c.id AS carportId, c.type, c.price, c.name, c.length, c.width, c.height, c.roof_angle, c.shed_length, c.shed_width " +
                    "FROM `user` AS u " +
                    "INNER JOIN `order` AS o " +
                    "ON u.id = o.user_id " +
                    "INNER JOIN query AS q " +
                    "ON o.query_id = q.id " +
                    "INNER JOIN link_carport_query AS l " +
                    "ON q.id = l.query_id " +
                    "INNER JOIN carport AS c " +
                    "ON l.carport_id = c.id " +
                    "WHERE o.user_id = ? AND o.message != ?;";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setString(2, "standard order");
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String message = rs.getString("message");
                    int price = rs.getInt("price");
                    int orderId = rs.getInt("id");
                    int queryId = rs.getInt("queryId");
                    String status = rs.getString("status");
                    String type = rs.getString("type");
                    String carportName = rs.getString("name");
                    Timestamp created = rs.getTimestamp("created");
                    double totalPrice = rs.getDouble("totalPrice");
                    int quantity = rs.getInt("quantity");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int roof_angle = rs.getInt("roof_angle");
                    int shed_length = rs.getInt("shed_length");
                    int shed_width = rs.getInt("shed_width");

                    GetAll getAll = new GetAll(queryId, orderId, status, message, created, totalPrice, quantity, type, price, carportName, length, width, height, roof_angle, shed_length, shed_width);
                    checkForMsgs.add(getAll);
                }
                return checkForMsgs;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}