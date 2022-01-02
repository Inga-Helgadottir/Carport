package web.commands.team;

import business.entities.Carport;
import business.entities.Query;
import business.entities.User;
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
    //private MaterialCalculator materialCalculator;

    public GetOffers(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        carportFacade = new CarportFacade(database);
        //materialCalculator = new MaterialCalculator(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getId();
            Query offer = queryFacade.getQuery("offered", user_id);
<<<<<<< HEAD
            if (offer!= null){
=======
            if (offer != null) {
>>>>>>> timmy
                Carport carport = carportFacade.getCarportByQuery(offer);
                offer.setCarport(carport);
                request.setAttribute("offer", offer);
            }
            else {
<<<<<<< HEAD
                request.setAttribute("error", "Ingen tilbud");
=======
                request.setAttribute("error","Afventer stadig tilbud");
>>>>>>> timmy
            }
            return pageToShow;


        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
