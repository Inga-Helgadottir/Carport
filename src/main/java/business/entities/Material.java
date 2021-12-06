package business.entities;

public class Material {

    private  String name;
    private int width;
    private int height;
    private int length;
    private  String category;
    private double cost;

    private int material_id;
    private String description;
    private int quantity;






    public Material(String name,int width, int height, int length, String category, double cost) {

    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}
