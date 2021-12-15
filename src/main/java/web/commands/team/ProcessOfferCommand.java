package web.commands.team;

import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessOfferCommand extends CommandProtectedPage {
    private final QueryFacade queryFacade;
    private final OrderFacade orderFacade;

    public ProcessOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int query_id = Integer.parseInt(request.getParameter("query_id"));
            int carport_id = Integer.parseInt(request.getParameter("carport_id"));
            if (request.getParameter("accept") != null) {
                queryFacade.updateQueryStatus("ordered", query_id);
                //udfyld link tabel med materiale id'er.: magter jeg ikke lige nu
                //id,status,created,price,msg,userID,queryID


                //orderFacade.makeOrder()
                //oprette en ordre i databasen med 'ordered' status
                //
            }
            if (request.getParameter("annul") != null) {
                //ændre query status til 'cancelled'
                //udfylde link tabel med materiale id'er.
                //måske oprette en ordre i databasen med 'cancelled' status
            }
            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
