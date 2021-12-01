package web.commands.tim;

import business.entities.Carport;
import business.exceptions.UserException;
import business.services.CarportFacade;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EnkeltCarportCommand extends CommandUnprotectedPage {

    public EnkeltCarportCommand(String pageToShow) {
        super(pageToShow);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        CarportFacade carportFacade;
        carportFacade = new CarportFacade(database);
        try {
            List<Carport> enkeltcarporte = carportFacade.getEnkeltcarporte();
            request.setAttribute("enkeltcarporte",enkeltcarporte);

        } catch (UserException ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return pageToShow;
    }
}
