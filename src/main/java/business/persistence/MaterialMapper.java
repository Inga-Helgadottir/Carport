package business.persistence;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

public Material getMaterial(String name)throws UserException{
    try (Connection connection = database.connect()) {
        String sql = "SELECT * FROM material WHERE `name` = ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int length = rs.getInt("length");
                String category = rs.getString("category");
                double cost = rs.getDouble("cost");
                String description = rs.getString("description");
                Material m = new Material(name,width,height,length,category,cost,description);
                m.setMaterial_id(id);
                m.setDescription(description);
                return m;
            }
            else {
                throw new UserException("Could not find material"+ name);
            }

        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    } catch (SQLException e) {
        throw new UserException(e.getMessage());
    }

}

    public Material getMaterialByCategory(String name) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material WHERE name = ?";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    String category = rs.getString("category");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material m = new Material(name,width,height,length,category,cost,description);
                    m.setMaterial_id(id);
                    m.setDescription(description);
                    return m;
                }
                else {
                    throw new UserException("kan ikke finde materiale "+ name);
                }

            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }


    public Material SelectMaterialByCategory(String name, int length) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `name` = ? and length = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, length);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int material_id = rs.getInt("id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    String category = rs.getString("category");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material m = new Material(name,width,height,length,category,cost,description);
                    m.setMaterial_id(material_id);
                    return m;
                }
                else {
                    throw new UserException("Could not find material " + name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Material getRafters(int beamheight, int beamwidth, int beamlength, String name) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE width=? AND height=? AND length=? AND `name`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, beamwidth);
                ps.setInt(2, beamheight);
                ps.setInt(3, beamlength);
                ps.setString(4, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    double cost = rs.getDouble("cost");
                    String category = rs.getString("category");
                    String description = rs.getString("description");
                    Material m = new Material(name,width,height,length,category,cost,description);
                    m.setMaterial_id(id);
                    return m;
                } else {
                    throw new UserException("Could not find rafter");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }

    }

    public Material getOthers(String name) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE name = ? ";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int material_id = rs.getInt("id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    String category = rs.getString("category");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material m = new Material(name,width,height,length,category,cost,description);
                    m.setMaterial_id(material_id);
                    return m;
                }
                else {
                    throw new UserException("Could not find material " + name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Material getShedScrews(String category)throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `category` = ? ";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int material_id = rs.getInt("id");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    String description = rs.getString("description");
                    double cost = rs.getDouble("cost");
                    Material m = new Material(name,width,height,length,category,cost,description);
                    m.setMaterial_id(material_id);
                    return m;
                }
                else {
                    throw new UserException("Could not find material " + category);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
/*
    public Material getMaterialByCategory2(int length, String category) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE length=? AND category=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, length);
                ps.setString(2, category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material material = new Material(name, width, height, length, category, cost);
                    material.setMaterial_id(id);
                    material.setDescription(description);
                    material.setMaterial_id(id);
                    return material;
                } else {
                    throw new UserException("Could not find rafter");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }

    }

    public Material getMaterialByCategory(int length, String mat_category) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE length=? AND category=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, length);
                ps.setString(2, mat_category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int mat_width = rs.getInt("width");
                    int mat_height = rs.getInt("height");
                    int mat_length = rs.getInt("length");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material material = new Material(name, mat_width, mat_height, mat_length, mat_category, cost);
                    material.setMaterial_id(id);
                    material.setDescription(description);
                    return material;
                } else {
                    throw new UserException("Could not find material under this category with the with the specified length");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Material> getAllMaterialsByCategory(String category) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material  WHERE category = ? ORDER BY length";
            List<Material> materials = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, category);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material mat = new Material(name, width, height, length, category, cost);
                    mat.setDescription(description);
                    mat.setMaterial_id(id);
                    materials.add(mat);
                }
                return materials;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

 */

