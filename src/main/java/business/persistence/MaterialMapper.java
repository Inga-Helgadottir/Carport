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

    public Material getRafters(int beamheight, int beamwidth, int beamlength, String category) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `width`=? AND `height`=? AND `length`=? AND `category`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, beamwidth);
                ps.setInt(2, beamheight);
                ps.setInt(3, beamlength);
                ps.setString(4,category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material material = new Material(name, width, height, length, category, cost);
                    material.setMaterial_id(id);
                    material.setDescription(description);
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

    public Material getMaterialByCategory(String mat_category, int length) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `category`=? AND `length`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, mat_category);
                ps.setInt(2, length);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int mat_width = rs.getInt("width");
                    int mat_height = rs.getInt("height");
                    int mat_length = rs.getInt("length");
                    String category = rs.getString("category");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material material = new Material(name, mat_width, mat_height, mat_length, category, cost);
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
}
