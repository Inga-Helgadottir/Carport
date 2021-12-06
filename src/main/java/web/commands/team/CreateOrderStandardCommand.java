package web.commands.team;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Query;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacede;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CreateOrderStandardCommand extends CommandProtectedPage {
    QueryFacade queryFacade;
    OrderFacede orderFacede;

    public CreateOrderStandardCommand(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        orderFacede = new OrderFacede(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Opret ordre
            List<Carport> shoppingcart = (List<Carport>) request.getSession().getAttribute("shoppingcart");
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();
            double price = 0;
            for (Carport c : shoppingcart) {
                price += c.getPrice() * c.getQuantity();
            }
            Query query = new Query("standard", price, userId, "standard order");
            Query query1 = queryFacade.makeQuery(query, shoppingcart);
            Date date = new Date();
            long time = date.getTime();
            Timestamp created = new Timestamp(time);
            Order order = new Order("standard", created, price, "standard order", query1.getId(), userId);
            orderFacede.createQuery(order);
            shoppingcart.clear();
            request.getSession().setAttribute("shoppingcart", shoppingcart);
            request.getSession().setAttribute("total", 0);
            return pageToShow;


        } catch (UserException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
