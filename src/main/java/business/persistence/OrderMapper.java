package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

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
    public Order makeOrder(Order order, List<Material> BOM, int carport_id) throws UserException {

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
                for (Material m : BOM) {
                    linkTabel(carport_id,m.getMaterial_id(),m.getQuantity());
                }
                return order;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    private void linkTabel(int carport_id, int material_id, int quantity) throws UserException{
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO link_tabel (carport_id, material_id, quantity) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ps.setInt(2, material_id);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }


}
