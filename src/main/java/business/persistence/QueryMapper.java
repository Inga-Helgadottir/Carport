package business.persistence;

import business.entities.Carport;
import business.entities.Query;
import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class QueryMapper {
    private Database database;

    public QueryMapper(Database database) {
        this.database = database;
    }

//    public List<Carport> getAllQueries(){
//    }

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
}
