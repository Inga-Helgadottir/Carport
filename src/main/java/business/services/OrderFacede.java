package business.services;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

import java.sql.SQLException;

public class OrderFacede {
    OrderMapper orderMapper;

    public OrderFacede(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public void createQuery(Order order) throws UserException, SQLException {
        orderMapper.createQuery(order);
    }
}