package web.commands.team;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Query;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.OrderFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderInfo extends CommandProtectedPage {
    private MaterialCalculator materialCalculator;
    private QueryFacade queryFacade;
    private CarportFacade carportFacade;
    private OrderFacade orderFacade;

    public GetOrderInfo(String pageToShow, String role) {
        super(pageToShow, role);
        materialCalculator = new MaterialCalculator(database);
        queryFacade = new QueryFacade(database);
        carportFacade = new CarportFacade(database);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            User user = (User) request.getSession().getAttribute("user");
            int query_id = orderFacade.getQueryId(order_id);
            Query query = queryFacade.getQuery(query_id);
            Carport carport = carportFacade.getCarportByQuery(query);
            Order order = orderFacade.getOrder(order_id);
            order.setQuery(query);
            order.setCarport(carport);
            order.setBOM(materialCalculator.calcBOM(carport.getLength(), carport.getWidth(), carport.getShed_length(), carport.getShed_width()));
            order.setUser(query.getUser());
            request.setAttribute("order", order);
            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
