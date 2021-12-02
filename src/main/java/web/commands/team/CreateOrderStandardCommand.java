package web.commands.team;

import business.entities.Carport;
import business.exceptions.UserException;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateOrderStandardCommand extends CommandProtectedPage {

    public CreateOrderStandardCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Carport> shoppingcart = (List<Carport>) request.getSession().getAttribute("shoppingcart");




        int userId = (int) request.getSession().getAttribute("userId");
        //Order o = new Order(price, userId, queryId, carportId);
        // orderFacede.createQuery(o);
        return pageToShow;
    }
}
