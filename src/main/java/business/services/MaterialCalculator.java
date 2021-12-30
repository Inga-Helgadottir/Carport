package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalculator {
    public final List<Material> materialList;
    private final MaterialFacade materialFacade;

    public MaterialCalculator(Database database) {
        materialFacade = new MaterialFacade(database);
        this.materialList = new ArrayList<>();
    }

    // beregn BOM
    public List<Material> calcBOM(int carport_length, int carport_width, int shed_length, int shed_width) throws UserException {
        materialList.clear();
        calcPost(carport_length, carport_width, shed_length, shed_width);
        calcRafters(carport_width, carport_length);
        calcRem(carport_length, carport_width);
        calcStern(carport_length, carport_width);
        if (shed_length != 0 && shed_width != 0) {
            calcPlanks(shed_length, shed_width);
            //calcShedStuff();            //løsholter til skur galv, løsholter til skur sider, 1 lægte til z på skurdør
        }
        calcRoof(carport_length, carport_width);


        calcOthers(shed_length, shed_width);
        if (shed_length != 0 && shed_width != 0) {
            //calcOthersShed(shed_length, shed_width);             //stalddørsgreb, hængsel, vinkelbeslag + 2 forskellige skruer.
        }
        return materialList;
    }

    //beklædning
    public void calcPlanks(int shed_length, int shed_width) throws UserException {
        double dist = 7.5;
        int amount_sider = (int) Math.ceil(shed_length / dist);
        int amount_topandbot = (int) Math.ceil(shed_length / dist);
        Material planks = materialFacade.SelectMaterialByCategory("brædt", 2100);
        int total = (int) (Math.ceil(amount_sider * 2) + Math.ceil(amount_topandbot * 2));
        planks.setQuantity(total);
    }

    //skruer
    public void calcOthers(int shed_length, int shed_width) throws UserException {

        Material firkantskiver = materialFacade.getOthers("firkantskiver");
        firkantskiver.setQuantity(12);
        materialList.add(firkantskiver);
        Material braeddebolt = materialFacade.getOthers("bræddebolt");
        braeddebolt.setQuantity(18);
        materialList.add(braeddebolt);
        Material beslagskruer = materialFacade.getOthers("beslagskruer");
        beslagskruer.setQuantity(3);
        materialList.add(beslagskruer);
        Material skruer = materialFacade.getOthers("skruer");
        skruer.setQuantity(1);
        materialList.add(skruer);
        Material beslag_left = materialFacade.getOthers("universal beslag venstre");
        beslag_left.setQuantity(15);
        materialList.add(beslag_left);
        Material beslag_rigth = materialFacade.getOthers("universal beslag højre");
        beslag_rigth.setQuantity(15);
        materialList.add(beslag_rigth);
        Material hulbaand = materialFacade.getOthers("hulbånd");
        hulbaand.setQuantity(2);
        materialList.add(hulbaand);
        Material plastmo_bundskruer = materialFacade.getOthers("plastmo bundskruer");
        plastmo_bundskruer.setQuantity(3);
        materialList.add(plastmo_bundskruer);

        if (shed_length != 0 && shed_width != 0) {
            Material skruer_skur = materialFacade.getShedScrews("Skruer 400 stk. 4,5x70 mm.");
            Material skruer_skur1 = materialFacade.getShedScrews("Skruer 300 stk. 4,5x50mm.");
            skruer_skur.setQuantity(2);
            skruer_skur1.setQuantity(2);
            materialList.add(skruer_skur);
            materialList.add(skruer_skur1);
            // standdørsgreb, t hængsel og vinkel beslag
        }
    }

    //pris
    public double getPrice(List<Material> materialList) {
        int price = 0;
        for (Material m : materialList) {
            price += (m.getCost() * m.getQuantity());
        }
        return price;
    }

    // tag
    public void calcRoof(int length, int width) throws UserException {
        if (width <= 3600) {
            Material roof = materialFacade.SelectMaterialByCategory("tag", 3600);
            roof.setQuantity(getRoofAmount(length));
            materialList.add(roof);
            Material roof1 = materialFacade.SelectMaterialByCategory("tag", 1200);
            roof1.setQuantity(getRoofAmount(length));
            materialList.add(roof1);
        } else if (width <= 4800) {
            Material roof = materialFacade.SelectMaterialByCategory("tag", 4800);
            roof.setQuantity(getRoofAmount(length));
            materialList.add(roof);
            Material roof1 = materialFacade.SelectMaterialByCategory("tag", 2400);
            roof1.setQuantity(getRoofAmount(length));
            materialList.add(roof1);

        } else if (width <= 6000) {
            Material roof = materialFacade.SelectMaterialByCategory("tag", 6000);
            roof.setQuantity(getRoofAmount(length));
            materialList.add(roof);
            Material roof1 = materialFacade.SelectMaterialByCategory("tag", 3600);
            roof1.setQuantity(getRoofAmount(length));
            materialList.add(roof1);
        }
    }

    //tag antal
    public int getRoofAmount(int length) {
        int amount = 0;
        if (length <= 3900) {
            amount = 2;
        } else if (length <= 4800) {
            amount = 3;
        } else if (length <= 5700) {
            amount = 4;
        } else if (length <= 6600) {
            amount = 5;
        } else if (length <= 7800) {
            amount = 6;
        }
        return amount;
    }

    //rem
    public void calcRem(int length, int width) throws UserException {
        if (width <= 4300) {
            Material rem = materialFacade.SelectMaterialByCategory("rem", length);
            rem.setQuantity(2);
            materialList.add(rem);
        } else if (width <= 6000) {
            Material rem = materialFacade.SelectMaterialByCategory("rem", length);
            rem.setQuantity(3);
            materialList.add(rem);
        }
    }

    //stolper
    public Material calcPost(int length, int width, int shed_length, int shed_width) throws UserException {
        Material post = materialFacade.SelectMaterialByCategory("stolpe", 3000);
        double test = width * 0.001;
        double test2 = length * 0.001;
        double squarefeet = test * test2;
        int shedposts = 0;
        shedposts = 2;
        if (shed_width == width){
            shedposts = 2;
        }

        if (shed_length != 0 && shed_width != 0) {
            if (squarefeet >= 44) {
                post.setQuantity(10);
            } else if (squarefeet >= 38.5) {
                post.setQuantity(8);
            } else if (squarefeet >= 33) {
                post.setQuantity(6);
            } else if (squarefeet >= 22) {
                post.setQuantity(4);
            } else if (squarefeet < 22) {
                post.setQuantity(4);
            }
        } else {
            if (squarefeet >= 44) {
                post.setQuantity(8);
            } else if (squarefeet >= 38.5) {
                post.setQuantity(8);
            } else if (squarefeet >= 37.5) {
                post.setQuantity(8);
            } else if (squarefeet >= 33) {
                post.setQuantity(6);
            } else if (squarefeet >= 27.5) {
                post.setQuantity(6);
            } else if (squarefeet >= 22) {
                post.setQuantity(4);
            } else if (squarefeet < 22) {
                post.setQuantity(4);
            }
        }
        materialList.add(post);
        return post;

    }

    //spær
    public Material calcRafters(int width, int length) throws UserException {
        int raftquantity;
        int beamspacing;
        int beamheight;
        int beamwidth = 45;
        String category = "spær";


        if (width >= 5700) {
            beamspacing = 600;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 5100) {
            beamspacing = 800;
            beamheight = 295;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 4800) {
            beamspacing = 600;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 4500) {
            beamspacing = 800;
            beamheight = 245;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 4200) {
            beamspacing = 600;
            beamheight = 220;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 3600) {
            beamspacing = 600;
            beamheight = 195;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 3300) {
            beamspacing = 600;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 3000) {
            beamspacing = 800;
            beamheight = 170;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 2700) {
            beamspacing = 600;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        } else if (width >= 2400) {
            beamspacing = 800;
            beamheight = 145;
            Material raft = materialFacade.getRafters(beamheight, beamwidth, width, category);
            raftquantity = getRaftQuantity(length, beamspacing, beamwidth);
            raft.setQuantity(raftquantity);
            raft.setBeamdist(beamspacing);
            materialList.add(raft);
            return raft;
        }
        return null;
    }

    //spær antal
    private int getRaftQuantity(int length, int beamspacing, int beamwidth) {
        int t = (int) Math.ceil((double) (length - beamwidth) / beamspacing);
        int diff = length - (t * beamspacing);

        if (diff >= 100) {
            return t + 3;
        }
        return t + 2;
    }

    //stern
    private void calcStern(int carport_length, int carport_width) throws UserException {
        //over og understern i siderne

        if (carport_length <= 5400) {
            Material m = materialFacade.SelectMaterialByCategory("understern", carport_length);
            if (carport_length == carport_width) {
                m.setQuantity(4);
                m.setDescription("understernbrædder til siderne, front og bagende");
                materialList.add(m);

                Material m1 = materialFacade.SelectMaterialByCategory("overstern", carport_length);
                m1.setQuantity(3);
                m1.setDescription("oversternbrædder til siderne, front og bagende");
                materialList.add(m1);
            } else {
                m.setQuantity(2);
                m.setDescription("understernbrædder til siderne");
                materialList.add(m);

                Material m1 = materialFacade.SelectMaterialByCategory("overstern", carport_length);
                m1.setQuantity(2);
                m1.setDescription("oversternbrædder til siderne");
                materialList.add(m1);
            }
        } else {
            int max_sider = 5400;
            int rest_sider = carport_length - max_sider;
            int max_fab = 3600;
            int rest_fab = carport_width - max_fab;


            if (rest_fab != rest_sider) {
                Material m2 = materialFacade.SelectMaterialByCategory("understern", max_sider);
                m2.setQuantity(2);
                m2.setDescription("understernbrædder til siderne");
                materialList.add(m2);

                Material m3 = materialFacade.SelectMaterialByCategory("understern", rest_sider);
                m3.setQuantity(2);
                m3.setDescription("rest understernbrædder til siderne");
                materialList.add(m3);

                Material m4 = materialFacade.SelectMaterialByCategory("overstern", max_sider);
                m4.setQuantity(2);
                m4.setDescription("oversternbrædder til siderne");
                materialList.add(m4);

                Material m5 = materialFacade.SelectMaterialByCategory("overstern", rest_sider);
                m5.setQuantity(2);
                m5.setDescription("rest oversternbrædder til siderne");
                materialList.add(m5);
            } else {
                Material m2 = materialFacade.SelectMaterialByCategory("understern", max_sider);
                m2.setQuantity(2);
                m2.setDescription("understernbrædder til siderne");
                materialList.add(m2);

                Material m3 = materialFacade.SelectMaterialByCategory("understern", rest_sider);
                m3.setQuantity(4);
                m3.setDescription("rest understernbrædder til siderne og front og bagende");
                materialList.add(m3);

                Material m4 = materialFacade.SelectMaterialByCategory("overstern", max_sider);
                m4.setQuantity(2);
                m4.setDescription("oversternbrædder til siderne");
                materialList.add(m4);

                Material m5 = materialFacade.SelectMaterialByCategory("overstern", rest_sider);
                m5.setQuantity(4);
                m5.setDescription("rest oversternbrædder til siderne og front og bagende");
                materialList.add(m5);
            }
        }

        //over og understern i for og bagende
        if (carport_width <= 3600) {
            if (carport_length != carport_width) {
                Material m6 = materialFacade.SelectMaterialByCategory("understern", carport_width);
                m6.setQuantity(2);
                m6.setDescription("understernbrædder til for og bag ende");
                materialList.add(m6);

                Material m7 = materialFacade.SelectMaterialByCategory("overstern", carport_width);
                m7.setQuantity(1);
                m7.setDescription("oversternbrædder til forenden");
                materialList.add(m7);
            }
        } else {
            int max_sider = 5400;
            int rest_sider = carport_length - max_sider;
            int max_fab = 3600;
            int rest_fab = carport_width - max_fab;

            if (rest_fab != rest_sider) {
                Material m8 = materialFacade.SelectMaterialByCategory("understern", max_fab);
                m8.setQuantity(2);
                m8.setDescription("understernbrædder til for og bag ende");
                materialList.add(m8);

                Material m9 = materialFacade.SelectMaterialByCategory("understern", rest_fab);
                m9.setQuantity(2);
                m9.setDescription("rest understernbrædder til for og bag ende");
                materialList.add(m9);

                Material m10 = materialFacade.SelectMaterialByCategory("overstern", max_fab);
                m10.setQuantity(1);
                m10.setDescription("oversternbrædder til forende");
                materialList.add(m10);

                Material m11 = materialFacade.SelectMaterialByCategory("overstern", rest_fab);
                m11.setQuantity(1);
                m11.setDescription("rest oversternbrædder til forende");
                materialList.add(m11);
            } else {
                Material m8 = materialFacade.SelectMaterialByCategory("understern", max_fab);
                m8.setQuantity(2);
                m8.setDescription("understernbrædder til for og bag ende");
                materialList.add(m8);

                Material m10 = materialFacade.SelectMaterialByCategory("overstern", max_fab);
                m10.setQuantity(1);
                m10.setDescription("oversternbrædder til forende");
                materialList.add(m10);

            }

        }
    }
}



