package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalculator {
    private List<Material> materialList;
    private OrderFacade orderFacade;
    private MaterialFacade materialFacade;

    public MaterialCalculator(Database database) {
        materialList = new ArrayList<>();
        orderFacade = new OrderFacade(database);
        materialFacade = new MaterialFacade(database);
    }
    public void calcRem(int length){
        Material rem = materialFacade.getMaterialByCategory("rem",length);
        rem.setQuantity(2);
        materialList.add(rem);
    }
    public void calcRoof(int length){
        Material roof = materialFacade.getMaterialByCategory("roof",length);
        roof.setMaterial_id(6);//TODO
        Material roof_under = materialFacade.getMaterialByCategory("roof",length/2);
        roof_under.setQuantity(6);
    }

    public int calcPost(int length, int width) throws UserException {
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

    public void calcRafters(int beamQuantity, int width, int length, boolean isDouble) throws UserException {
        int raftquantity = 0;
        int beamspacing = 0;
        int beamheight;
        int beamwidth = 45;


        if (width >= 5700) {
            beamspacing = 600;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 5100) {
            beamspacing = 800;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4800) {
            beamspacing = 600;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4500) {
            beamspacing = 800;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4200) {
            beamspacing = 600;
            beamheight = 220;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3600) {
            beamspacing = 600;
            beamheight = 195;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3300) {
            beamspacing = 600;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3000) {
            beamspacing = 800;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 2700) {
            beamspacing = 600;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 2400) {
            beamspacing = 800;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        }


        switch (width) {
            case 6000:
                System.out.println("Monday");
                break;
            case 5700:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }

    }

    private int getRaftQuantity(int length, int beamspacing, int beamwidth) {
        int Quantity = 0;
        int t = (int) Math.floor((double) (length - beamwidth) / beamspacing);
        int diff = length - (t * beamspacing);

        if (diff >= 101) {
            return Quantity = t + 2;
        }
        return Quantity = t + 1;
    }
}
