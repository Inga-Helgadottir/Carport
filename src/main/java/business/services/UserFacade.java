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

    public void changeQueryPrice(int queryId, String msg){
        userMapper.changeQueryPrice(queryId, msg);
    }

    public User getUserById(int id) throws UserException {
        return userMapper.getUserById(id);
    }

}
