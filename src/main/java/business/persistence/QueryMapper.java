package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryMapper {
    private Database database;

    public QueryMapper(Database database) {
        this.database = database;
    }


    public Query makeQuery(Query query, List<Carport> carports) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `query` (`status`, price, message, user_id) VALUES (?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, query.getStatus());
                ps.setDouble(2, query.getPrice());
                ps.setString(3, query.getMessage());
                ps.setInt(4, query.getUser_id());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int query_id = ids.getInt(1);
                query.setId(query_id);

                //link stuff
                for (Carport c : carports) {
                    link_carport_query(c.getId(),query_id,c.getQuantity());
                }
                return query;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    private void link_carport_query(int carportID, int query_id, int quantity) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO link_carport_query (carport_id, query_id, quantity) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportID);
                ps.setInt(2, query_id);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public List<Query> getAllQueries(String status) throws  UserException{
        List<Query> queries = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query`  WHERE `status` = ?";
            List<Material> materials = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int user_id = rs.getInt("user_id");
                    Query query = new Query(status,price,user_id,msg);
                    query.setId(id);
                   // query.setBOM(BOM);
                }
                return queries;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public List<QueryCollection> getQueryCollection() {
        List<QueryCollection> getAllList = new ArrayList<>();
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

                    QueryCollection getAll = new QueryCollection(status, message, created, totalPrice, userId, userName, userEmail, userPhone, quantity, carportId, type, price, carportName, length, width, height, roof_angle, shed_length, shed_width, queryId);
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