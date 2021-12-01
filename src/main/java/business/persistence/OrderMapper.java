package business.persistence;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void createQuery(Order order) throws UserException, SQLException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `query` (`price`, `carport_id`, `user_id`) VALUES (?, ?, ?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, order.getPrice());
                ps.setInt(2, order.getCarportId());
                ps.setInt(3, order.getUserId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                createOrder(order);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }

    public void createOrder(Order order) throws UserException, SQLException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (`price`, `query_id`, `user_id`) VALUES (?, ?, ?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, order.getPrice());
                ps.setInt(2, order.getQueryId());
                ps.setInt(3, order.getUserId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
}
