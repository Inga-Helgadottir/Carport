package web.commands.team;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Query;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetQueryInfo extends CommandProtectedPage {
    private final QueryFacade queryFacade;
    private final CarportFacade carportFacade;
    private final MaterialCalculator materialCalculator;

    public GetQueryInfo(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        carportFacade = new CarportFacade(database);
        materialCalculator = new MaterialCalculator(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int query_id = Integer.parseInt(request.getParameter("query_id"));
            Query query = queryFacade.getQuery(query_id);
            Carport carport = carportFacade.getCarportByQuery(query);
            query.setCarport(carport);
            List<Material> BOM = materialCalculator.calcBOM(carport.getLength(), carport.getWidth());
            query.setBOM(BOM);

            //pris halløjsa skal gøres i front end med scripts
            request.setAttribute("query", query);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
