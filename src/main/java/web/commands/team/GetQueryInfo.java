package web.commands.team;

import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetQueryInfo extends CommandProtectedPage {

    public GetQueryInfo(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {



        return pageToShow;

    }
}