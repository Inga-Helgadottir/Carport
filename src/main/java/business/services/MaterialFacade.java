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

    public Material getMaterial(String name) throws UserException {
        return materialMapper.getMaterial(name);
    }
    public Material getMaterialByCategory(String name) throws UserException {
        return materialMapper.getMaterialByCategory(name);
    }

    public Material SelectMaterialByCategory(String name, int length) throws UserException {
        return materialMapper.SelectMaterialByCategory(name,length);
    }
    public Material getRafters(int beamheight, int beamwidth, int beamlength, String category) throws UserException {
        return materialMapper.getRafters(beamheight, beamwidth, beamlength, category);
    }

    public Material getOthers(String name) throws UserException {
       return materialMapper.getOthers(name);
    }

    public Material getShedScrews(String category)throws UserException {
        return materialMapper.getShedScrews(category);
    }
}
/*
    public Material getMaterialByCategory(int length, String category) throws UserException {
        return materialMapper.getMaterialByCategory(length,category);
    }
    public Material getMaterialByCategory2(int length, String category) throws UserException {
        return materialMapper.getMaterialByCategory2(length,category);
    }

    public List<Material> getAllMaterialsByCategory(String category) throws UserException{
        return materialMapper.getAllMaterialsByCategory(category);
    }

 */



