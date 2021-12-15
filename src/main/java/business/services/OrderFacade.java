package business.services;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public Order makeOrder(Order order) throws UserException {
        return orderMapper.makeOrder(order);
    }
    public Order makeOrder(Order order, List<Material>BOM, int carport_id) throws UserException {
        return orderMapper.makeOrder(order,BOM,carport_id);
    }
}
