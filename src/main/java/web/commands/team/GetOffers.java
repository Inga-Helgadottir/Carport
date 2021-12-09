package web.commands.team;

import business.entities.Carport;
import business.entities.Query;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetOffers extends CommandProtectedPage {
    private QueryFacade queryFacade;
    private CarportFacade carportFacade;
    private MaterialCalculator materialCalculator;

    public GetOffers(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        carportFacade = new CarportFacade(database);
        materialCalculator = new MaterialCalculator(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int user_id = (int) request.getSession().getAttribute("userID");
            Query query = queryFacade.getQuery("requested", user_id);
            Carport carport = carportFacade.getCarportByQuery(query);
            query.setCarport(carport);
            query.setBOM(materialCalculator.calcBOM(carport.getLength(), carport.getWidth()));
            request.getSession().setAttribute("offer", query);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
