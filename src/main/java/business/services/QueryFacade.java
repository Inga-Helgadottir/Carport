package business.services;
import business.entities.*;
import business.entities.Carport;
import business.entities.Query;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.QueryMapper;

import java.util.List;

public class QueryFacade {
    QueryMapper queryMapper;

    public QueryFacade(Database database) {
        queryMapper = new QueryMapper(database);
    }


    public Boolean checkForQuery(String status, int user_id) throws UserException {
        return queryMapper.checkForQuery(status,user_id);
    }

    public Query getQuery(String status, int user_id) throws UserException {
        return queryMapper.getQuery(status, user_id);
    }

    public Query getQuery(int query_id) throws UserException {
        return queryMapper.getQuery(query_id);
    }
    public User getUser(int user_id) throws  UserException{
        return queryMapper.getUser(user_id);

    }

    public Query makeQuery(Query query, List<Carport> carports) throws UserException {
        return queryMapper.makeQuery(query, carports);
    }

    public void customCarportQuery(Carport carport, Query query) throws UserException {
        queryMapper.customCarportQuery(carport, query);
    }

    public List<Query> getAllQueries(String status) throws UserException {
        return queryMapper.getAllQueries(status);
    }

    public void updateQueryStatus(String status, int query_id) throws UserException {
        queryMapper.updateQueryStatus(status, query_id);
    }

    public void updateQueryPrice(double salesprice, int query_id, int carport_id)throws UserException {
        queryMapper.updateQueryPrice(salesprice,query_id,carport_id);
    }
}
