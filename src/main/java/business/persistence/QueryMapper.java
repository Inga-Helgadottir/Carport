package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryMapper {
    private final Database database;

    public QueryMapper(Database database) {
        this.database = database;
    }


    public void fillLinkTable(int carport_id, int query_id, int quantity) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO link_carport_query (carport_id, query_id, quantity) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
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

    public Query makeQueryCustom(Query query) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `query` (`status`, price, message, user_id, created) VALUES (?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, query.getStatus());
                ps.setDouble(2, query.getPrice());
                ps.setString(3, query.getMessage());
                ps.setInt(4, query.getUser_id());
                ps.setTimestamp(5, query.getCreated());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int query_id = ids.getInt(1);
                query.setId(query_id);


                return query;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Query customCarportQuery(Carport carport, Query query) throws UserException {
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

                Query q = makeQueryCustom(query);
                fillLinkTable(carport_id, q.getId(), 1);

                q.setCarport(carport);
                return q;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public boolean checkForQuery(String status,String status1, int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query` WHERE `status`=? OR `status`=? AND user_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setString(2, status1);
                ps.setInt(3, user_id);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage()+"cfq"+status+status1);
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Query getQuery(int query_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query` WHERE `id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, query_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String status = rs.getString("status");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int user_id = rs.getInt("user_id");
                    Timestamp created = rs.getTimestamp("created");
                    Query query = new Query(status, price, user_id, msg, created);
                    query.setId(id);
                    User user = getUser(user_id);
                    query.setUser(user);

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

    public Query getQuery(String status, int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query` WHERE `status`=? AND user_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    Timestamp created = rs.getTimestamp("created");
                    Query query = new Query(status, price, user_id, msg, created);
                    query.setId(id);
                    return query;
                } else {
                    return null;

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public Query makeQuery(Query query, List<Carport> carports) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `query` (`status`, price, message, user_id, type, created) VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, query.getStatus());
                ps.setDouble(2, query.getPrice());
                ps.setString(3, query.getMessage());
                ps.setInt(4, query.getUser_id());
                ps.setString(5, query.getStatus());
                ps.setTimestamp(6, query.getCreated());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int query_id = ids.getInt(1);
                query.setId(query_id);

                //link stuff
                for (Carport c : carports) {
                    link_carport_query(c.getId(), query_id, c.getQuantity());
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


    public List<Query> getAllQueries(String status) throws UserException {
        List<Query> queries = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query`  WHERE `status` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    int user_id = rs.getInt("user_id");
                    Timestamp created = rs.getTimestamp("created");
                    Query query = new Query(status, price, user_id, msg, created);
                    query.setId(id);
                    User user = getUser(user_id);
                    query.setUser(user);
                    queries.add(query);
                }
                return queries;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    private User getUser(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user` WHERE `id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    int telephone = rs.getInt("telephone");
                    int address_id = rs.getInt("address_id");
                    String address = rs.getString("address");
                    User user = new User(email, password, role);
                    user.setName(name);
                    user.setTelephone(telephone);
                    user.setAddress_id(address_id);
                    user.setAddress(address);
                    user.setId(user_id);
                    String cityname = getCity(address_id);
                    user.setCity(cityname);
                    return user;
                } else {
                    throw new UserException("Could not find user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    private String getCity(int address_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT city_id FROM `address` WHERE `id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, address_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int city_id = rs.getInt("city_id");
                    return getCityName(city_id);
                } else {
                    throw new UserException("Could not find city" + address_id);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    private String getCityName(int city_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT `name` FROM `city` WHERE `id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, city_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    throw new UserException("Could not find city" + city_id);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public void updateQueryStatus(String status, int query_id) {
        try (Connection connection = database.connect()) {
            String sql = "update `query` set `status` = ? where id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, query_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
//skal kun bruges hvis kunden kan mere end en forespørgsel ad gangen.
    public List<Query> getUserQueries(String status, int user_id) throws UserException {
        List<Query> queries = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `query`  WHERE `status` = ? AND user_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, user_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    double price = rs.getDouble("price");
                    String msg = rs.getString("message");
                    Query query = new Query(status, price, user_id, msg);
                    query.setId(id);
                    queries.add(query);
                }
                return queries;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

 */
