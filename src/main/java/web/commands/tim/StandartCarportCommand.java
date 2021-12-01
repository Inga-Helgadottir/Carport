package web.commands.tim;

import business.entities.Carport;
import business.exceptions.UserException;
import business.services.CarportFacade;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StandartCarportCommand extends CommandUnprotectedPage {

    public StandartCarportCommand(String pageToShow) {
        super(pageToShow);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        return pageToShow;
    }
}
