package business.services;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Query;
import business.entities.QueryCollection;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.QueryMapper;

import java.util.List;

public class QueryFacade {
    QueryMapper queryMapper;

    public QueryFacade(Database database) {
        queryMapper = new QueryMapper(database);
    }


    public Query makeQuery(Query query, List<Carport> carports) throws UserException {
        return queryMapper.makeQuery(query, carports);
    }

    public Query makeQueryCustom(Query query) throws UserException {
        return queryMapper.makeQueryCustom(query);
    }

    public List<Query> getAllQueries(String status) throws UserException {
        return queryMapper.getAllQueries(status);
    }
}
