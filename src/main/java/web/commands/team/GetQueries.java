package web.commands.team;

import business.entities.Query;
import business.entities.QueryCollection;
import business.exceptions.UserException;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetQueries extends CommandProtectedPage {
    QueryFacade queryFacade;

    public GetQueries(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Query> queries = queryFacade.getAllQueries("requested");
            request.setAttribute("queries", queries);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
