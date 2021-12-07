package web.commands.team;

import business.entities.Material;
import business.entities.Query;
import business.exceptions.UserException;
import business.services.MaterialCalculator;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendRequest extends CommandProtectedPage {
    private MaterialCalculator materialCalculator;

    public SendRequest(String pageToShow, String role) {
        super(pageToShow, role);
        materialCalculator = new MaterialCalculator(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int carport_width = Integer.parseInt(request.getParameter("width"));
            int carport_length = Integer.parseInt(request.getParameter("length"));
            int user_id = (int) request.getSession().getAttribute("user_id");
            String msg = request.getParameter("message");
            List<Material> BOM = materialCalculator.calcBOM(carport_length, carport_width);
            Query query = new Query("custom", 10, user_id, msg);
            query.setBOM(BOM);
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }


        return pageToShow;
    }
}
