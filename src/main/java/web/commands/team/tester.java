package web.commands.team;

import business.entities.GetAll;
import business.exceptions.UserException;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.util.List;


public class tester extends CommandUnprotectedPage
{
    public tester(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        //in jsp script i calc funktionen = document.cookie = "mm=" + mm;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mm")) {
                    System.out.println(cookie.getValue());
                }
            }
        }
        return pageToShow;
    }
}
