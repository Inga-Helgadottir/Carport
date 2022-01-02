package web.commands.team;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateDimensions extends CommandProtectedPage {
    private final CarportFacade carportFacade;
    private final MaterialCalculator materialCalculator;
    private final QueryFacade queryFacade;


    public UpdateDimensions(String pageToShow, String role) {
        super(pageToShow, role);
        carportFacade = new CarportFacade(database);
        materialCalculator = new MaterialCalculator(database);
        queryFacade = new QueryFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int carport_id = Integer.parseInt(request.getParameter("carport_id"));
            int query_id = Integer.parseInt(request.getParameter("query_id"));
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            carportFacade.updateCarportLength(length, carport_id);
            carportFacade.updateCarportWidth(width, carport_id);
            List<Material> materials = materialCalculator.calcBOM(length, width,0,0);
            double price = materialCalculator.getPrice(materials);
            queryFacade.updateQueryPrice(price, query_id, carport_id);

            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error ", e.getMessage());
            return pageToShow;
        }
    }
}
