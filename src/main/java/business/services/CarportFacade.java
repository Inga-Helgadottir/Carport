package business.services;

import business.entities.Carport;
import business.exceptions.UserException;
import business.persistence.CarportMapper;
import business.persistence.Database;

import java.util.List;

public class CarportFacade {
    CarportMapper carportMapper;

    public CarportFacade(Database database) {
        carportMapper = new CarportMapper(database);
    }


    public List<Carport> getEnkeltcarporte() throws UserException {
        return carportMapper.getEnkeltcarporte();
    }
}
