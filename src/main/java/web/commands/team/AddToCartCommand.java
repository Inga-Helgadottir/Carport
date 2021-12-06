package web.commands.team;

import business.entities.Carport;
import business.exceptions.UserException;
import business.services.CarportFacade;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddToCartCommand extends CommandProtectedPage {
    CarportFacade carportFacade;
    boolean triggered;

    public AddToCartCommand(String pageToShow, String role) {
        super(pageToShow, role);
        carportFacade = new CarportFacade(database);
        triggered = false;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int carportID = Integer.parseInt(request.getParameter("carportID"));
            Carport carport = carportFacade.getEnkeltCarport(carportID);

            List<Carport> shoppingcart = (List<Carport>) request.getSession().getAttribute("shoppingcart");
            if (shoppingcart == null) {
                shoppingcart = new ArrayList<>();
            }
            carport.setQuantity(quantity);
            shoppingcart.add(carport);
            double total = calculateTotal(shoppingcart);
            request.getSession().setAttribute("shoppingcart", shoppingcart);
            request.getSession().setAttribute("total", total);
            return pageToShow;

        } catch (UserException exception) {
            request.setAttribute("error", exception.getMessage());
            return pageToShow;
        }
    }

    private double calculateTotal(List<Carport> shoppingcartlist) {
        double total = 0;
        for (Carport c : shoppingcartlist) {
            total += (c.getPrice() * c.getQuantity());
        }
        return total;
    }
}
