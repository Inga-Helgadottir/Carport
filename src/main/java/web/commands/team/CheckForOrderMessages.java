package web.commands.team;

import business.entities.GetAll;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.QueryMapper;
import business.services.GetAllFacede;
import business.services.QueryFacade;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckForOrderMessages extends CommandProtectedPage {
    GetAllFacede getAllFacede;
    public CheckForOrderMessages(String pageToShow, String role) {
        super(pageToShow, role);
        getAllFacede = new GetAllFacede(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User u = (User) request.getSession().getAttribute("user");
            int userId = u.getId();
            request.getSession().setAttribute("queryMsgs", getAllFacede.checkForMessages(userId));
        } catch (UserException e) {
            e.printStackTrace();
        }
        return pageToShow;
    }
}
