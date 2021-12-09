package web.commands.team;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Query;
import business.exceptions.UserException;
import business.persistence.MaterialMapper;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.MaterialFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendRequest extends CommandProtectedPage {
    private MaterialCalculator materialCalculator;
    private QueryFacade queryFacade;
    private MaterialFacade materialFacade;
    private CarportFacade carportFacade;

    public SendRequest(String pageToShow, String role) {
        super(pageToShow, role);
        materialCalculator = new MaterialCalculator(database);
        queryFacade = new QueryFacade(database);
        materialFacade = new MaterialFacade(database);
        carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int user_id = (int) request.getSession().getAttribute("userID");
            int carport_width = Integer.parseInt(request.getParameter("width"));
            int carport_length = Integer.parseInt(request.getParameter("length"));
            String msg = request.getParameter("message");
            //check om der allerede er en query fra denne bruger
            if (!queryFacade.checkForQuery("requested", user_id)) {
                //query og carport oprettes//query og carport og link tabel udfyldes
                Query query = new Query("requested", 0, user_id, msg);
                Carport carport = new Carport(carport_length, carport_width, 3000, 15, 0, 0, "custom", "custom", 0, "info");
                Query q = queryFacade.customCarportQuery(carport, query);
                /*
                //Bill Of Material  og pris udregnes
                List<Material> BOM = materialCalculator.calcBOM(carport_length, carport_width);
                //double price = materialCalculator.getPrice(BOM);
                for (Material m : BOM) {
                    System.out.println("material name: " + m.getName() + "  dimensions: " + m.getWidth() + "x" + m.getLength() + "  ID: " + m.getMaterial_id() + "  quantity: " + m.getQuantity());
                }
                 */
                // hvis der allerede er en query fra burgeren
            } else {
                request.setAttribute("error", "Der var et problem med oprettelsen af din forespørgsel. " +
                        "Hvis du allerede har en forespørgsel skal du først tage stilling til det tilhørende tilbud." +
                        "Hvis du ikke er tilfreds med det tilbud så kan du afvise tilbuddet og oprette en ny forespørgel" +
                        "eller du kan ringe på 2020202002 og få svar på eventuelle spørgsmål.");
            }
            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
