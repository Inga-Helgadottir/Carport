package business.services;

import business.entities.GetAll;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.GetAllMapper;

import java.util.List;

public class GetAllFacede {
    GetAllMapper getAllMapper;

    public GetAllFacede(Database database) {
        getAllMapper = new GetAllMapper(database);
    }

    public List<GetAll> GetAll() {
        return getAllMapper.GetAll();
    }

    public List<GetAll> checkForMessages(int userId) throws UserException {
        return getAllMapper.checkForMessages(userId);
    }


}
