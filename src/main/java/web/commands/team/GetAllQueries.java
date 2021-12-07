package web.commands.team;
import business.entities.GetAll;
import business.persistence.GetAllMapper;
import business.services.GetAllFacede;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllQueries extends CommandProtectedPage {
    GetAllFacede getAllFacede;

    public GetAllQueries(String pageToShow, String role) {
        super(pageToShow, role);
        getAllFacede = new GetAllFacede(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<GetAll> getAllList = (List<GetAll>) request.getSession().getAttribute("getAllList");
        request.getSession().setAttribute("getAllList", getAllFacede.GetAll());
        return pageToShow;
    }
}
