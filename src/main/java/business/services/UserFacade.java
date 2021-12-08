package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void changeQueryMsg(int queryId, String msg){
        userMapper.changeQueryMsg(queryId, msg);
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

}
