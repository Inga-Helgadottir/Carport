package business.services;

import business.entities.Carport;
import business.entities.Query;
import business.exceptions.UserException;
import business.persistence.CarportMapper;
import business.persistence.Database;

import java.util.List;

public class CarportFacade {
    CarportMapper carportMapper;

    public CarportFacade(Database database) {
        carportMapper = new CarportMapper(database);
    }


    public Carport createCarport(Carport carport) throws UserException {
        return carportMapper.createCarport(carport);
    }

    public List<Carport> getEnkeltcarporte(String type) throws UserException {
        return carportMapper.getEnkeltcarporte(type);
    }

    public Carport getEnkeltCarport(int carportID) throws UserException {

        return carportMapper.getEnkeltCarport(carportID);
    }

    public Carport getCarportByQuery(Query query) throws UserException {
        return carportMapper.getCarportByQuery(query);
    }

    public Carport getCarport(int carport_id) throws UserException {
        return carportMapper.getCarport(carport_id);
    }


    public void updateCarportLength(int length, int carport_id) throws UserException{
        carportMapper.updateCarportLength(length,carport_id);
    }
    public void updateCarportWidth(int width, int carport_id) throws UserException{
        carportMapper.updateCarportWidth(width,carport_id);
    }
}
