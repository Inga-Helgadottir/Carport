package web.commands.team;

import business.exceptions.UserException;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectOfferCommand extends CommandProtectedPage {
    UserFacade userFacade;
    public RejectOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String ids = request.getParameter("orderCheck");
        String[] idArr = ids.split("/");
        int queryId = Integer.parseInt(idArr[0]);
        System.out.println(queryId);

        String msg = "standard order";
        userFacade.changeQueryMsg(queryId, msg);
        return pageToShow;
    }
}
