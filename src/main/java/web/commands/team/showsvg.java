package web.commands.team;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.MaterialCalculator;
import business.services.ShowSVG;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class showsvg extends CommandUnprotectedPage {
    ShowSVG showSVG;
    MaterialCalculator materialCalculator;


    public showsvg(String pageToShow) {
        super(pageToShow);
        materialCalculator = new MaterialCalculator(database);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int carport_width = Integer.parseInt(request.getParameter("width"));
        int carport_length = Integer.parseInt(request.getParameter("length"));
        int shed_length = Integer.parseInt(request.getParameter("shed_length"));
        int shed_width = Integer.parseInt(request.getParameter("shed_width"));
        List<Material> BOM = materialCalculator.calcBOM(carport_length,carport_width,shed_length,shed_width);
        showSVG = new ShowSVG(BOM,carport_length,carport_width,shed_length,shed_width);
        String svg = showSVG.CreateSVG();
        request.setAttribute("svg",svg);



        return pageToShow;
    }
}
