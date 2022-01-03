package web.commands.team;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetOrders extends CommandProtectedPage {
    private OrderFacade orderFacade;

    public GetOrders(String pageToShow, String role) {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            List<Order> orderList = orderFacade.getUserOrders("ordered", user.getId());
            request.setAttribute("orderlist", orderList);
            return pageToShow;
        } catch (UserException exception) {
            request.setAttribute("error", exception.getMessage());
            return pageToShow;
        }
    }
}
