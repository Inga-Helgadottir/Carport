package web.commands.team;

import business.exceptions.UserException;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StandartCarportCommand extends CommandUnprotectedPage {

    public StandartCarportCommand(String pageToShow) {
        super(pageToShow);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        return pageToShow;
    }
}
