package business.persistence;

import business.entities.Order;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public Order makeOrder(Order order) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (`status`, `created`, price, `message`, user_id, query_id) VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getStatus());
                ps.setTimestamp(2, order.getCreated());
                ps.setDouble(3, order.getPrice());
                ps.setString(4, order.getMessage());
                ps.setInt(5, order.getUser_id());
                ps.setInt(6, order.getQuery_id());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int order_id = ids.getInt(1);
                order.setId(order_id);
                return order;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
