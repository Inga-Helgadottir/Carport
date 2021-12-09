package web.commands.team;

import business.entities.User;
import business.exceptions.UserException;
import business.services.GetAllFacede;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptOfferCommand extends CommandProtectedPage {
    UserFacade userFacade;
    GetAllFacede getAllFacede;
    public AcceptOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        getAllFacede = new GetAllFacede(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String ids = request.getParameter("orderCheck");
        String[] idArr = ids.split("/");
        int queryId = Integer.parseInt(idArr[0]);
        int orderId = Integer.parseInt(idArr[1]);
        int newPrice = Integer.parseInt(idArr[2]);

        String msg = "standard order";
        userFacade.changeOrderMessage(orderId, msg);

        userFacade.changeQueryPrices(queryId, newPrice);//--------fix
        userFacade.changeOrderPrices(orderId, newPrice);

        request.getSession().setAttribute("getAllList", getAllFacede.GetAll());

        return pageToShow;
    }
}
