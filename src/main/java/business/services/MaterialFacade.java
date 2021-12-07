package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.util.List;

public class MaterialFacade {
    MaterialMapper materialMapper;


    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public Material getRafters(int beamheight, int beamwidth, int beamlength) throws UserException {
        return materialMapper.getRafters(beamheight, beamwidth, beamlength);
    }

    public Material getMaterialByCategory(String category, int length) throws UserException {
        return materialMapper.getMaterialByCategory(category,length);
    }

    public List<Material> getAllMaterialsByCategory(String category) throws UserException{
        return materialMapper.getAllMaterialsByCategory(category);
    }


}
