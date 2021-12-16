package web.commands.team;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.MaterialCalculator;
import business.services.OrderFacade;
import business.services.QueryFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProcessOfferCommand extends CommandProtectedPage {
    private final QueryFacade queryFacade;
    private final OrderFacade orderFacade;
    private final MaterialCalculator materialCalculator;
    private final CarportFacade carportFacade;

    public ProcessOfferCommand(String pageToShow, String role) {
        super(pageToShow, role);
        queryFacade = new QueryFacade(database);
        orderFacade = new OrderFacade(database);
        materialCalculator = new MaterialCalculator(database);
        carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int carport_id = Integer.parseInt(request.getParameter("carport_id"));
            int query_id = Integer.parseInt(request.getParameter("query_id"));
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            double offerprice = Double.parseDouble(request.getParameter("offerprice"));
            Date date = new Date();
            long time = date.getTime();
            Timestamp created = new Timestamp(time);

            //lav BOM
            Carport carport = carportFacade.getCarport(carport_id);
            List<Material> BOM = materialCalculator.calcBOM(carport.getLength(), carport.getWidth());


            //fix listen: det er ret svært :( Man skal finde objekter i listen, som har fælles værdier til materiale_id attribut.
            //det kan godt lade sig gøre at finde dubletter, men det kræver at man iterere over hele listen x antal gange(hvor x er antal materialer i listen).
            // det virker som en rigtig dårlig måde at gøre det på. Jeg tror jeg bliver nød til at sørge for at listen ikke opretter dubletter istedet.
            // så behøver man ikke sortere noget som helst og vi undgår at iterere listen en million gange.



            if (request.getParameter("accept") != null) {
                //updater query
                queryFacade.updateQueryStatus("ordered", query_id);
                //opret ordre
                Order order = new Order("ordered", created, offerprice, "msg", query_id, user_id);
                orderFacade.makeOrder(order, BOM, carport_id);
            }
            if (request.getParameter("annul") != null) {
                queryFacade.updateQueryStatus("cancelled", query_id);
                Order order = new Order("cancelled", created, offerprice, "msg", query_id, user_id);
                orderFacade.makeOrder(order, BOM, carport_id);
                //udfylde link tabel med materiale id'er.
                //måske oprette en ordre i databasen med 'cancelled' status
            }
            return pageToShow;
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}
/*
    Comparator<Material> comparator = new Comparator<Material>() {
        @Override
        public int compare(Material o1, Material o2) {
            return o1.getMaterial_id() - o2.getMaterial_id();
        }
    };
            BOM.sort(comparator);

 */