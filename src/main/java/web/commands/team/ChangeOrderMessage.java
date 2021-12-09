package web.commands.team;

import business.services.GetAllFacede;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeOrderMessage extends CommandProtectedPage {
    GetAllFacede getAllFacede;
    UserFacade userFacade;
    public ChangeOrderMessage(String pageToShow, String role) {
        super(pageToShow, role);
        getAllFacede = new GetAllFacede(database);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("orderCheck");
        String[] idArr = ids.split("/");
        int queryId = Integer.parseInt(idArr[0]);
        int newPrice = Integer.parseInt(request.getParameter("inputSaldo" + queryId));

        String msg = String.valueOf(newPrice);
        userFacade.changeOrderMessage(queryId, msg);

        request.getSession().setAttribute("getAllList", getAllFacede.GetAll());

        return pageToShow;

    }
}
