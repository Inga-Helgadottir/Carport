package web.commands;

import business.exceptions.UserException;
import business.persistence.Database;
import web.commands.team.*;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    //Return a token string from the execute method to make a client side redirect,
    // instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";
    public final static String WAS_NOT_FOUND_COMMAND = "404_NOT_FOUND";

    private static HashMap<String, Command> commands;
    public static Database database;

    private static void initCommands(Database database) {
        //--------------------commands from startcode---------------------
        commands = new HashMap<>();
        commands.put("index", new CommandUnprotectedPage("index"));
        commands.put("loginpage", new CommandUnprotectedPage("loginpage"));
        commands.put("logincommand", new LoginCommand("index"));
        commands.put("logoutcommand", new LogoutCommand(""));
        commands.put("registerpage", new CommandUnprotectedPage("registerpage"));
        commands.put("registercommand", new RegisterCommand(""));
        commands.put("customerpage", new CommandProtectedPage("customerpage", "customer"));
        commands.put("employeepage", new CommandProtectedPage("employeepage", "employee"));
        //-----------------------------links---------------------------------------------------------------------------
        commands.put("cartpage", new CommandProtectedPage("shoppingcartpage", "customer"));
        commands.put("standartcarportpage", new StandartCarportCommand("standartcarport"));
        commands.put("quickbuildpage", new CommandProtectedPage("quickbuildpage", "customer"));
        //----------------------------commands-------------------------------------------------------------------------
        commands.put("addtocart", new AddToCartCommand("standartcarport", "customer"));
        commands.put("updatecommand", new UpdateCartCommand("shoppingcartpage", "customer"));
        commands.put("createorderstandard", new CreateOrderStandardCommand("customerpage", "customer"));
        //---------------------    ------------------------------------------------------------------------------------
        commands.put("sendrequest", new SendRequest("customerpage", "customer"));
        commands.put("queries", new GetQueries("queriespage", "employee"));
        commands.put("queryinfo", new GetQueryInfo("querypage", "employee"));
        commands.put("offers", new GetOffers("offerspage", "customer"));
        commands.put("offerinfo", new GetOfferInfo("offerpage", "customer"));
        commands.put("sendoffer", new SendOfferCommand("queriespage","employee"));
        commands.put("processoffer", new ProcessOfferCommand("offerspage", "customer"));

    }

    public static Command fromPath(HttpServletRequest request, Database db) {
        String action = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + action);

        if (commands == null) {
            database = db;
            initCommands(database);
        }

        return commands.getOrDefault(action, new CommandUnknown());   // unknowncommand is default
    }

    public abstract String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws UserException;

}
