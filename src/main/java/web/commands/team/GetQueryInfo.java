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

public class GetQueryInfo extends CommandProtectedPage {
    private QueryFacade queryFacade;
    private CarportFacade carportFacade;
    private MaterialCalculator materialCalculator;

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
           //int user_id = Integer.parseInt(request.getParameter("user_id"));

            Query query = queryFacade.getQuery(query_id);
            Carport carport = carportFacade.getCarportByQuery(query);
            query.setCarport(carport);
            query.setBOM(materialCalculator.calcBOM(carport.getLength(), carport.getWidth()));
          //regn pris forskellene ud



            request.setAttribute("query", query);

            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
