package web.commands.team;

import business.entities.Carport;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateCartCommand extends CommandProtectedPage {

    public UpdateCartCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Carport> shoppingcart = (List<Carport>) request.getSession().getAttribute("shoppingcart");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            //update quantity
            if (request.getParameter("update") != null){
                int index_update = Integer.parseInt(request.getParameter("update"));
                shoppingcart.get(index_update).setQuantity(quantity);
            }
            //remove item from shoppingCart
            if (request.getParameter("remove") != null) {
                int index_remove = Integer.parseInt(request.getParameter("remove"));
                shoppingcart.remove(index_remove);
            }
            double total = total(shoppingcart);
            request.getSession().setAttribute("shoppingcart", shoppingcart);
            request.getSession().setAttribute("total", total);
            return pageToShow;

        } catch (NumberFormatException x) {
            request.setAttribute("error", x.getMessage());
            return pageToShow;
        }
    }

    private double total(List<Carport> shoppingcartlist) {
        double total = 0;
        for (Carport c : shoppingcartlist) {
            total += (c.getPrice() * c.getQuantity());
        }
        return total;
    }
}
