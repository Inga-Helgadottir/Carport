package web.commands.team;

import business.entities.Material;
import business.entities.Query;
import business.exceptions.UserException;
import business.persistence.MaterialMapper;
import business.services.MaterialCalculator;
import business.services.MaterialFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendRequest extends CommandProtectedPage {
    private MaterialCalculator materialCalculator;
    private QueryFacade queryFacade;
    private MaterialFacade materialFacade;

    public SendRequest(String pageToShow, String role) {
        super(pageToShow, role);
        materialCalculator = new MaterialCalculator(database);
        queryFacade = new QueryFacade(database);
        materialFacade = new MaterialFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            int carport_width = Integer.parseInt(request.getParameter("width"));
            int carport_length = Integer.parseInt(request.getParameter("length"));
            List<Material> BOM = materialCalculator.calcBOM(carport_length, carport_width);
            //double price = materialCalculator.getPrice(BOM);
            int user_id = (int) request.getSession().getAttribute("userID");
            String msg = request.getParameter("message");
            Query query = new Query("custom", 0, user_id, msg);
            query.setBOM(BOM);
            queryFacade.makeQueryCustom(query);
            for (Material m : BOM) {
                System.out.println("material name: " +m.getName()+ "  dimensions: "+m.getWidth()+"x"+m.getLength()+"  ID: "+m.getMaterial_id()+"  quantity: "+m.getQuantity());

            }
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
