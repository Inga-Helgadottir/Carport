package web.commands;

import business.entities.User;
import business.persistence.Database;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends CommandUnprotectedPage
{
    private UserFacade userFacade;

    public RegisterCommand(String pageToShow)
    {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        int telephone = Integer.parseInt(request.getParameter("telephone"));
        int zipcode = Integer.parseInt(request.getParameter("zipcode"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");

        if (password1.equals(password2))
        {
            User tmp = new User(email,password2,"customer");
            tmp.setName(name);
            tmp.setTelephone(telephone);

            tmp.setZipcode(zipcode);
            tmp.setCity(city);
            tmp.setAddress(address);

            int zipcodeId = userFacade.createUserCheckZipcode(tmp);
            int cityId = userFacade.createUserCheckCity(tmp);

            if(zipcodeId == 0){
                zipcodeId = userFacade.createUserZipcode(tmp);
            }

            tmp.setZipcodeId(zipcodeId);

            if(cityId == 0){
                cityId = userFacade.createUserCity(tmp);
            }

            tmp.setCityId(cityId);

            int addressId = userFacade.createUserAddress(tmp);
            tmp.setAddress_id(addressId);

            User user = userFacade.createUser(tmp);
            HttpSession session = request.getSession();

            session.setAttribute("email", email);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            return user.getRole() + "page";

        }
        else
        {
            request.setAttribute("error", "the two passwords did not match");
            return "registerpage";
        }
    }

}
