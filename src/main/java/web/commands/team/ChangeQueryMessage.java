package web.commands.team;

import business.entities.GetAll;
import business.entities.Query;
import business.entities.User;
import business.exceptions.UserException;
import business.services.GetAllFacede;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeQueryMessage extends CommandProtectedPage {
    GetAllFacede getAllFacede;
    UserFacade userFacade;
    public ChangeQueryMessage(String pageToShow, String role) {
        super(pageToShow, role);
        getAllFacede = new GetAllFacede(database);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String ids = request.getParameter("orderCheck");
            String[] idArr = ids.split("/");
            int queryId = Integer.parseInt(idArr[0]);
            int userId = Integer.parseInt(idArr[1]);

            int newPrice = Integer.parseInt(request.getParameter("inputSaldo" + queryId));

            String msg = String.valueOf(newPrice);
            userFacade.changeQueryMsg(queryId, msg);

            User u = userFacade.getUserById(userId);
            u.setMessage(msg);

            request.getSession().setAttribute("getAllList", getAllFacede.GetAll());
        } catch (UserException e) {
            e.printStackTrace();
        }

        return pageToShow;

    }
}
