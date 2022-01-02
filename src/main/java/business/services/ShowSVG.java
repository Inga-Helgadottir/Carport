package business.services;

import business.entities.Material;
import business.entities.SVG;

import java.util.List;

public class ShowSVG {
    private int width;
    private int length;
    private int shed_length;
    private int shed_width;
    private List<Material> BOM;
    private int length_buffer;
    private int width_buffer;
    private SVG svg;
    private final int u_width;


    public ShowSVG(List<Material> c_BOM, int carport_length, int carport_width, int c_shed_length, int c_shed_width) {
        width = carport_width / 10;
        length = carport_length / 10;
        shed_length = c_shed_length / 10;
        shed_width = c_shed_width / 10;
        BOM = c_BOM;
        length_buffer = 60;
        width_buffer = 60;
        u_width = 35;

        // boolean hasShed = shed_length != 0 && shed_width != 0; // is true if shed_length and width are different from 0.
    }

    public String CreateSVG() {
        int view = 1000;
        int view1 = 1000;
        svg = new SVG(0, 0, "0 0 " + view + " " + view1 + "", 75, 75);
        spær();
        firkant();
        striper();
        stopler();
        rem();
        pile();

        return svg.toString();
    }

    public void pile() {
        int tmp = 35;
        int tmp2 = (int) ((width_buffer + width) * 0.5);
        svg.addDefs();
        svg.addArrowLine(length_buffer - tmp, width_buffer + width, length_buffer - tmp, width_buffer);//y
        svg.addArrowLine(length_buffer, width_buffer + width + tmp, length_buffer + length, width_buffer + width + tmp); //x
        svg.addText();
        svg.addText(length_buffer - tmp,tmp2, -90, width ); // horisontal linjes tekst
        // svg.addText((arrowLineFullLength * 0.5) + 50 , arrowLineFullHeight + 40, 0, arrowLineFullLength + " cm");
        //svg.addDashLine(100, 50, 600, fullHeight - 50);
        //svg.addDashLine(100, fullHeight - 50, 600, 50);
    }

    public void firkant() {
        //vertikal
        svg.addLine(length_buffer, width_buffer, length_buffer + length, width_buffer);
        svg.addLine(length_buffer, width_buffer + width, length_buffer + length, width_buffer + width);
        //horisontal
        svg.addLine(length_buffer, width_buffer, length_buffer, width_buffer + width);
        svg.addLine(length_buffer + length, width_buffer, length_buffer + length, width_buffer + width);
    }

    public void striper() {
        int udhæng_length = 30;
        // Skur
        svg.addDashRect(length_buffer + length - shed_length - udhæng_length, width_buffer + u_width, shed_length, shed_width);
    }

    public void rem() {
        int rem_tykkelse = 10;
        svg.addrectempty(length_buffer + 0, width_buffer + u_width, length, rem_tykkelse);
        svg.addrectempty(length_buffer + 0, width_buffer + width - u_width - rem_tykkelse, length, rem_tykkelse);
    }

    public void spær() {
        int spær_antal = 0;
        int spærdist = 0;
        for (Material m : BOM) {
            if (m.getName().equals("spær")) {
                spær_antal = m.getQuantity() - 1;
                spærdist = m.getBeamdist() / 10;
            }
        }
        // Spær
        int tmp = (spær_antal * 5) + (spærdist * (spær_antal - 1));
        if (tmp > length) {
            spær_antal = spær_antal - 1;
            svg.addrectempty(length_buffer + length, width_buffer, 5, width);
        }
        for (int x = 0; x < spær_antal; x++) {
            svg.addrectempty(length_buffer + (spærdist * x), width_buffer, 5, width);
        }
    }

    public void stopler() {
        int stolpe_antal = 0;
        int stolpe_tykkelse = 10;
        int udhæng_bredde = 35;
        int udhæng_back = 100;
        int udhæng_front = 100;
        for (Material m : BOM) {
            if (m.getName().equals("stolpe")) {
                stolpe_antal = m.getQuantity();
            }
        }
        if (length <= 420) {
            udhæng_front = 30;
        }
        if (shed_length != 0 && shed_width != 0) {
            udhæng_back = 30;
        }

        svg.addStolpe(length_buffer + udhæng_front, width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse); //top venstre
        svg.addStolpe(length_buffer + udhæng_front, width_buffer + (width - udhæng_bredde - stolpe_tykkelse), stolpe_tykkelse, stolpe_tykkelse); // bund venstre


        //intet skur regler
        if (shed_length == 0 && shed_width == 0) {
            svg.addStolpe(length_buffer + (length - udhæng_front - stolpe_tykkelse), width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse); //top højre
            svg.addStolpe(length_buffer + (length - udhæng_front - stolpe_tykkelse), width_buffer + (width - udhæng_bredde - stolpe_tykkelse), stolpe_tykkelse, stolpe_tykkelse); //bund højre

            if (stolpe_antal >= 8) {
                int tmp = (udhæng_back + udhæng_front) + (4 * stolpe_tykkelse);
                int dist = ((length - tmp) / 3);
                //top
                svg.addStolpe(length_buffer + udhæng_front + stolpe_tykkelse + dist, width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + udhæng_front + ((stolpe_tykkelse + dist) * 2), width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
                //bund
                svg.addStolpe(length_buffer + udhæng_front + stolpe_tykkelse + dist, width_buffer + (width - udhæng_bredde - stolpe_tykkelse), stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + udhæng_front + ((stolpe_tykkelse + dist) * 2), width_buffer + (width - udhæng_bredde - stolpe_tykkelse), stolpe_tykkelse, stolpe_tykkelse);
            } else if (stolpe_antal >= 6) {
                int tmp1 = (udhæng_back + udhæng_front) * 2 + (3 * stolpe_tykkelse);
                int dist1 = (length - tmp1) / 2;
                svg.addStolpe(length_buffer + (udhæng_front + stolpe_tykkelse + dist1), width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + (udhæng_front + stolpe_tykkelse + dist1), width_buffer + (width - udhæng_bredde - stolpe_tykkelse), stolpe_tykkelse, stolpe_tykkelse);
            }

        } else {
            //med skur regler
            svg.addStolpe(length_buffer + length - udhæng_back - stolpe_tykkelse, width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
            svg.addStolpe(length_buffer + length - udhæng_back - stolpe_tykkelse, width_buffer + udhæng_bredde + shed_width - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
            svg.addStolpe(length_buffer + length - udhæng_back - shed_length, width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
            svg.addStolpe(length_buffer + length - udhæng_back - shed_length, width_buffer + udhæng_bredde + shed_width - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
            if (stolpe_antal <= 6) {
                svg.addStolpe(length_buffer + length - udhæng_back - stolpe_tykkelse, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
            } else if (stolpe_antal <= 8) {
                svg.addStolpe(length_buffer + length - udhæng_back - stolpe_tykkelse, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + length - udhæng_back - shed_length, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
            } else if (stolpe_antal <= 10) {
                svg.addStolpe(length_buffer + length - udhæng_back - stolpe_tykkelse, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + length - udhæng_back - shed_length, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
                int tmp1 = (length - udhæng_front - udhæng_back - shed_length - stolpe_tykkelse) / 2;
                int buffer = 50;
                svg.addStolpe(length_buffer + udhæng_front + tmp1 + stolpe_tykkelse + buffer, width_buffer + udhæng_bredde, stolpe_tykkelse, stolpe_tykkelse);
                svg.addStolpe(length_buffer + udhæng_front + tmp1 + stolpe_tykkelse + buffer, width_buffer + width - udhæng_bredde - stolpe_tykkelse, stolpe_tykkelse, stolpe_tykkelse);
            }
        }
    }
}




