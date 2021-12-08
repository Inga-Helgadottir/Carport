package web.commands.team;

import business.exceptions.UserException;
import business.services.GetAllFacede;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectOfferCommand extends CommandProtectedPage {
    UserFacade userFacade;
    GetAllFacede getAllFacede;
    public RejectOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        getAllFacede = new GetAllFacede(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String ids = request.getParameter("orderCheck");
        String[] idArr = ids.split("/");
        int queryId = Integer.parseInt(idArr[0]);
        System.out.println(queryId);

        String msg = "standard order";
        userFacade.changeQueryMsg(queryId, msg);

        request.getSession().setAttribute("getAllList", getAllFacede.GetAll());
        return pageToShow;
    }
}
