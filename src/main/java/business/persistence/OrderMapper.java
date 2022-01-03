package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
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
                    linkTabel(carport_id, m.getMaterial_id(), m.getQuantity());
                }
                return order;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    private void linkTabel(int carport_id, int material_id, int quantity) throws UserException {
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

    public List<Order> getUserOrders(String status, int user_id) throws UserException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order`  WHERE `status` = ? AND `user_id` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, user_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("id");
                    Timestamp ts = rs.getTimestamp("created");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int query_id = rs.getInt("query_id");
                    Order order = new Order(status, ts, price, msg, query_id, user_id);
                    Query query = getQuery(query_id);
                    int carportId = getCarportId(query_id);
                    Carport carport = getCarport(carportId);
                    order.setCarport(carport);
                    order.setQuery(query);
                    order.setId(order_id);
                    orders.add(order);
                }
                return orders;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    private int getCarportId(int query_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT carport_id FROM `link_carport_query` WHERE `query_id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, query_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("carport_id");

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

    private Carport getCarport(int carport_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `carport` WHERE `id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
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
                    carport.setId(carport_id);
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

    private Query getQuery(int query_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query` WHERE `id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, query_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int user_id = rs.getInt("user_id");
                    Timestamp created = rs.getTimestamp("created");
                    Query query = new Query(status, price, user_id, msg, created);
                    query.setId(query_id);
                    return query;
                } else {
                    throw new UserException("Cant find any queries with this status and user_id i database");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public Order getOrder(int order_id)  throws  UserException{
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order` WHERE `id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    Timestamp created = rs.getTimestamp("created");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int user_id = rs.getInt("user_id");
                    int query_id = rs.getInt("query_id");
                    Order order = new Order(status, created, price, msg, query_id, user_id);
                    order.setId(order_id);
                    return order;
                } else {
                    throw new UserException("Cant find any queries with this status and user_id i database");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }



    public int getQueryId(int order_id) throws UserException{
        try (Connection connection = database.connect()) {
            String sql = "SELECT query_id FROM `order` WHERE `id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("query_id");
                } else {
                    throw new UserException("Cant find any queries with this status and user_id i database");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
