package business.persistence;

import business.entities.GetAll;

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
            String sql = "SELECT o.status, o.message, o.created, o.price AS totalPrice, u.name AS username, u.email, u.telephone, l.quantity, c.id, c.type, c.price, c.name, c.length, c.width, c.height, c.roof_angle, c.shed_length, c.shed_width " +
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
                    int carportId = rs.getInt("id");
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
                    GetAll getAll = new GetAll(status, message, created, totalPrice, userName, userEmail, userPhone, quantity, carportId, type, price, carportName, length, width, height, roof_angle, shed_length, shed_width);
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
}