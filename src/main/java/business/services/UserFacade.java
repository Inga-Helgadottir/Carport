package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

public class UserFacade {
    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException {
        return userMapper.login(email, password);
    }

    public User createUser(User user) throws UserException {
        return userMapper.createUser(user);
    }

    public void changeOrderMessage(int queryId, String msg){
        userMapper.changeOrderMessage(queryId, msg);
    }

    public User getUserById(int id) throws UserException {
        return userMapper.getUserById(id);
    }

    public void changeQueryPrices(int queryId, int newPrice) {
        userMapper.changeQueryPrices(queryId, newPrice);
    }

    public void changeOrderPrices(int orderId, int newPrice) {
        userMapper.changeOrderPrices(orderId, newPrice);
    }

    //------------------------------------------------------------------------------------------------------------------NEW

    public int createUserCheckZipcode(User user) throws UserException {
        return userMapper.createUserCheckZipcode(user);
    }

    public int createUserZipcode(User user) throws UserException{
        return userMapper.createUserZipcode(user);
    }

    public int createUserCity(User user) throws UserException {
        return userMapper.createUserCity(user);
    }

    public int createUserCheckCity(User user) throws UserException {
        return userMapper.createUserCheckCity(user);
    }

    public int createUserAddress(User user) throws UserException {
        return userMapper.createUserAddress(user);
    }
    //------------------------------------------------------------------------------------------------------------------
}
