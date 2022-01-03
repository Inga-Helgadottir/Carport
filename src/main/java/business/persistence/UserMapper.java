package business.persistence;

import business.exceptions.UserException;
import business.entities.User;

import java.sql.*;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public User createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `user` (`name`,`email`, `password`, `role`, `telephone`, `address_id`) VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getRole());
                ps.setInt(5, user.getTelephone());
                ps.setInt(6, user.getAddress_id());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
                return user;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user` WHERE `email`=? AND `password`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    int telephone = rs.getInt("telephone");
                    int address_id = rs.getInt("address_id");
                    User user = new User(email,password,role);
                    user.setName(name);
                    user.setTelephone(telephone);
                    user.setAddress_id(address_id);
                    user.setId(id);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }




    public int createUserCheckZipcode(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT id FROM `zipcode` WHERE postalcode = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user.getZipcode());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    ResultSet ids = ps.getGeneratedKeys();
                    ids.next();
                    return id;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return 0;
    }

    public int createUserZipcode(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `zipcode` (`name`,`postalcode`) VALUES (?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getAddress());
                ps.setInt(2, user.getZipcode());
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setZipcodeId(id);
                return id;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int createUserCity(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `city` (`name`,`zipcode_id`) VALUES (?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getCity());
                ps.setInt(2, user.getZipcodeId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setCityId(id);
                return id;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int createUserCheckCity(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT id FROM `city` WHERE zipcode_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user.getZipcodeId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    ResultSet ids = ps.getGeneratedKeys();
                    ids.next();
                    return id;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return 0;
    }

    public int createUserAddress(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `address` (`address`,`city_id`) VALUES (?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getAddress());
                ps.setInt(2, user.getCityId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
                return id;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}
