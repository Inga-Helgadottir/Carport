package web.commands.team;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Query;
import business.entities.User;
import business.exceptions.UserException;
import business.services.MaterialCalculator;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class SendRequest extends CommandProtectedPage {
    private final MaterialCalculator materialCalculator;
    private final QueryFacade queryFacade;

    public SendRequest(String pageToShow, String role) {
        super(pageToShow, role);
        materialCalculator = new MaterialCalculator(database);
        queryFacade = new QueryFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("width").equals("") && request.getParameter("length").equals("")) {
            request.setAttribute("error", "Du skal angive dine ønskede dimensioner før du kan sende forespørgslen.");
            return pageToShow;
        }
        try {
            int carport_width = Integer.parseInt(request.getParameter("width"));
            int carport_length = Integer.parseInt(request.getParameter("length"));
            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getId();
            String msg = request.getParameter("message");

            //skal kun gøres hvis brugeren ikke har en query i forvejen
            if (!queryFacade.checkForQuery("requested","offered", user_id)) {
                //query + price + timestamp
                Date date = new Date();
                long time = date.getTime();
                Timestamp created = new Timestamp(time);
                List<Material> BOM = materialCalculator.calcBOM(carport_length, carport_width);
                double price = materialCalculator.getPrice(BOM);
                Query query = new Query("requested", price, user_id, msg, created);
                //carport
                Carport carport = new Carport(carport_length, carport_width, 3000, 15, 0, 0, "custom", "custom", price, "info");
                queryFacade.customCarportQuery(carport, query);

                // hvis der allerede er en query fra burgeren
            } else {
                request.setAttribute("error", "Der var et problem med oprettelsen af din forespørgsel. " +
                        "Hvis du allerede har en forespørgsel, men ikke har modtaget et tilbud endnu, må du vente på tilbuddet." +
                        " Hvis du ikke er tilfreds med det tilbud så kan du afvise tilbuddet og oprette en ny forespørgel derefter." +
                        " Ring på 49251225 og for at snakke med en medarbejder ");
            }
            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
