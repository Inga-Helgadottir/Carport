package web.commands.team;

import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendOfferCommand extends CommandProtectedPage {
    private final QueryFacade queryFacade;

    public SendOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int query_id = Integer.parseInt(request.getParameter("query_id"));
            queryFacade.updateQueryStatus("offered", query_id);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
