package business.services;

import business.entities.Material;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalculator {
    private List<Material> materialList;
    private OrderFacade orderFacade;

    public MaterialCalculator(Database database) {
        materialList = new ArrayList<>();
        orderFacade = new OrderFacade(database);
    }


    public int calcPost(int length, int width) {
        boolean isDouble = width >= 4300;
        double squarefeet = (double) (width / 1000) * (double) (length / 1000);
        int beamQuatity = 0;

        if (isDouble) {
            if (squarefeet >= 44) {
                beamQuatity = 10;
            } else if (squarefeet >= 38.5) {
                beamQuatity = 8;
            } else if (squarefeet >= 33) {
                beamQuatity = 7;
            } else if (squarefeet >= 22) {
                beamQuatity = 5;
            } else if (squarefeet < 22) {
                beamQuatity = 5;
            }
            return beamQuatity;
        } else {
            if (squarefeet >= 44) {
                beamQuatity = 9;
            } else if (squarefeet >= 38.5) {
                beamQuatity = 8;
            } else if (squarefeet >= 37.5) {
                beamQuatity = 7;
            } else if (squarefeet >= 33) {
                beamQuatity = 6;
            } else if (squarefeet >= 27.5) {
                beamQuatity = 5;
            } else if (squarefeet >= 22) {
                beamQuatity = 4;
            } else if (squarefeet < 22) {
                beamQuatity = 4;
            }
        }
        return beamQuatity;
    }


    public void calcRafters(int beamQuantity, int width, int length, boolean isHeavy, boolean isDouble) {
        // find spærafstand og dimensioner på spær med spærvidde på?

    }

}
