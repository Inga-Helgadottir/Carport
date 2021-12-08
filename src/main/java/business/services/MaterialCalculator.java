package business.services;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Query;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalculator {
    public final List<Material> materialList;
    private OrderFacade orderFacade;
    private MaterialFacade materialFacade;

    public MaterialCalculator(Database database) {
        orderFacade = new OrderFacade(database);
        materialFacade = new MaterialFacade(database);
        this.materialList = new ArrayList<>();
    }


    public List<Material> calcBOM(int carport_length, int carport_width) throws UserException {
        materialList.clear();
        calcPost(carport_length, carport_width);
        calcRafters(carport_width, carport_length);
        calcRem(carport_length, carport_width);
        calcStern(carport_length, carport_width);
        //calcRoof(carport_length);
        return materialList;
    }

    public double getPrice(List<Material> materialList) {
        int price = 0;
        for (Material m : materialList) {
            price += (m.getCost() * m.getQuantity());
        }
        return price;
    }

    public void calcRoof(int length) throws UserException {
        // Material roof = materialFacade.getMaterialByCategory("roof", length);
        //  roof.setMaterial_id(6);//TODO
        //Material roof_under = materialFacade.getMaterialByCategory("roof", length / 2);
        //roof_under.setQuantity(6);
    }


    public void calcRem(int length, int width) throws UserException {
        Material rem = materialFacade.SelectMaterialByCategory("rem", length);
        rem.setQuantity(2);
        materialList.add(rem);
    }

    public void calcPost(int length, int width) throws UserException {
        Material post = materialFacade.SelectMaterialByCategory("post", 3000);
        boolean isDouble = width >= 4300;
        double squarefeet = (double) (width / 1000) * (double) (length / 1000);

        if (isDouble) {
            if (squarefeet >= 44) {
                post.setQuantity(10);
            } else if (squarefeet >= 38.5) {
                post.setQuantity(8);
            } else if (squarefeet >= 33) {
                post.setQuantity(7);
            } else if (squarefeet >= 22) {
                post.setQuantity(5);
            } else if (squarefeet < 22) {
                post.setQuantity(5);
            }
        } else {
            if (squarefeet >= 44) {
                post.setQuantity(9);
            } else if (squarefeet >= 38.5) {
                post.setQuantity(8);
            } else if (squarefeet >= 37.5) {
                post.setQuantity(7);
            } else if (squarefeet >= 33) {
                post.setQuantity(6);
            } else if (squarefeet >= 27.5) {
                post.setQuantity(5);
            } else if (squarefeet >= 22) {
                post.setQuantity(4);
            } else if (squarefeet < 22) {
                post.setQuantity(4);
            }
        }
        materialList.add(post);
    }

    public void calcRafters(int width, int length) throws UserException {
        int raftquantity = 0;
        int beamspacing = 0;
        int beamheight;
        int beamwidth = 45;
        String category = "rafter";


        if (width >= 5700) {
            beamspacing = 600;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 5100) {
            beamspacing = 800;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4800) {
            beamspacing = 600;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4500) {
            beamspacing = 800;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 4200) {
            beamspacing = 600;
            beamheight = 220;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3600) {
            beamspacing = 600;
            beamheight = 195;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3300) {
            beamspacing = 600;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 3000) {
            beamspacing = 800;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 2700) {
            beamspacing = 600;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
        } else if (width >= 2400) {
            beamspacing = 800;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            materialList.add(raft);
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

    private void calcStern(int carport_length, int carport_width) throws UserException {
        //over og understern i siderne

        if (carport_length <= 5400) {
            Material m = materialFacade.SelectMaterialByCategory("understern", carport_length);
            m.setQuantity(2);
            m.setDescription("understernbrædder til siderne");
            materialList.add(m);

            Material m1 = materialFacade.SelectMaterialByCategory("overstern", carport_length);
            m1.setQuantity(2);
            m1.setDescription("oversternbrædder til siderne");
            materialList.add(m1);
        } else {
            int max_sider = 5400;
            int rest = carport_length - max_sider;
            Material m2 = materialFacade.SelectMaterialByCategory("understern", max_sider);
            m2.setQuantity(2);
            m2.setDescription("understernbrædder til siderne");
            materialList.add(m2);

            Material m3 = materialFacade.SelectMaterialByCategory("understern", rest);
            m3.setQuantity(2);
            m3.setDescription("rest understernbrædder til siderne");
            materialList.add(m3);

            Material m4 = materialFacade.SelectMaterialByCategory("overstern", max_sider);
            m4.setQuantity(2);
            m4.setDescription("oversternbrædder til siderne");
            materialList.add(m4);

            Material m5 = materialFacade.SelectMaterialByCategory("overstern", rest);
            m5.setQuantity(2);
            m5.setDescription("rest oversternbrædder til siderne");
            materialList.add(m5);
        }

        //over og understern i for og bagende
        if (carport_width <= 3600) {
            Material m6 = materialFacade.SelectMaterialByCategory("understern", carport_width);
            m6.setQuantity(2);
            m6.setDescription("understernbrædder til for og bag ende");
            materialList.add(m6);

            Material m7 = materialFacade.SelectMaterialByCategory("overstern", carport_width);
            m7.setQuantity(1);
            m7.setDescription("oversternbrædder til forenden");
            materialList.add(m7);
        } else {
            int max_forogbag = 3600;
            int rest = carport_width - max_forogbag;
            Material m8 = materialFacade.SelectMaterialByCategory("understern", max_forogbag);
            m8.setQuantity(2);
            m8.setDescription("understernbrædder til for og bag ende");
            materialList.add(m8);

            Material m9 = materialFacade.SelectMaterialByCategory("understern", rest);
            m9.setQuantity(2);
            m9.setDescription("rest understernbrædder til for og bag ende");
            materialList.add(m9);

            Material m10 = materialFacade.SelectMaterialByCategory("overstern", max_forogbag);
            m10.setQuantity(1);
            m10.setDescription("oversternbrædder til forende");
            materialList.add(m10);

            Material m11 = materialFacade.SelectMaterialByCategory("overstern", rest);
            m11.setQuantity(1);
            m11.setDescription("rest oversternbrædder til forende");
            materialList.add(m11);
        }

    }
}

       /*
        List<Material> understernList = materialFacade.getAllMaterialsByCategory("understern");
        List<Material> oversternList = materialFacade.getAllMaterialsByCategory("overstern");

        List<Material> availableMaterials_understern = new ArrayList<>();
        List<Material> availableMaterials_overstern = new ArrayList<>();
        for (Material m : understernList) {
            if (m.getLength() > carport_length) {
                availableMaterials_understern.add(m);
            }
        }
        for (Material m : oversternList) {
            if (m.getLength() > carport_length) {
                availableMaterials_overstern.add(m);
            }
        }
        Material overstern = availableMaterials_overstern.get(0);
        Material understern = availableMaterials_understern.get(0);

        //Material m = materialFacade.getSternByLength(stern_length)


        */

