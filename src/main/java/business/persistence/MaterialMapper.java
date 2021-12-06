package business.persistence;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper {
    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public Material getRafters(int beamheight, int beamwidth, int beamlength) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `width`=? AND `height`=? AND `length`=?" ;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, beamwidth);
                ps.setInt(2, beamheight);
                ps.setInt(3, beamlength);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    String category = rs.getString("category");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    Material material = new Material(name,width,height,length,category,cost);
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
}