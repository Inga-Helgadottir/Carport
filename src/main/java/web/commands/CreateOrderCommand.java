package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.OrderMapper;
import business.services.OrderFacede;
import business.services.UserFacade;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CreateOrderCommand extends CommandProtectedPage{
    OrderFacede orderFacede;
    public CreateOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        orderFacede = new OrderFacede(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        int userId = (int) request.getSession().getAttribute("userId");
        //de her skal komme fra en form men er ikke sikker hvor fra-----------------------------------------
        double price = Double.parseDouble(request.getParameter("price"));
        int queryId = Integer.parseInt(request.getParameter("queryId"));
        int carportId = Integer.parseInt(request.getParameter("carportId"));
        Order o = new Order(price, userId, queryId, carportId);
        try {
            orderFacede.createQuery(o);

            return REDIRECT_INDICATOR + pageToShow;
        }
        catch (UserException | SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "loginpage";//---------------------------------------------------CHANGE PAGE
        }
    }

}
