package web.commands.tim;

import business.entities.Carport;
import business.exceptions.UserException;
import business.services.CarportFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddToCartCommand extends CommandProtectedPage {
    CarportFacade carportFacade;

    public AddToCartCommand(String pageToShow, String role) {
        super(pageToShow, role);
        carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            System.out.println("quantity as parameter from form: " + quantity);
            int carportID = Integer.parseInt(request.getParameter("carportID"));
            System.out.println("carportID as parameter from form: " + carportID);
            Carport carport = carportFacade.getEnkeltCarport(carportID);
            carport.setQuantity(quantity);

            List<Carport> shoppingcart = (List<Carport>) request.getSession().getAttribute("shoppingcart");
            if (shoppingcart == null) {
                shoppingcart = new ArrayList<>();
            }

            shoppingcart.add(carport);
            double total = 0;
            for (Carport c : shoppingcart) {
                total += (c.getPrice() * quantity);
                System.out.println("carport name: " +c.getName());
            }
            request.getSession().setAttribute("shoppingcart", shoppingcart);
            request.getSession().setAttribute("total", total);
            return pageToShow;

        } catch (UserException exception) {
            request.setAttribute("error", exception.getMessage());
            return pageToShow;
        }
    }
}
