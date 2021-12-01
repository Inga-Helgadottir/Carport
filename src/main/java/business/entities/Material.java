package business.entities;

public class Material {
    private final String name;
    private double price;
    private final String category;
    private final int material_id;
    private final double width;
    private double length;
    private double height;
    private int quantity;


    public Material(int material_id, String name, double length, double width, double height, double price, String category) {
        this.material_id = material_id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.price = price;

        this.category = category;
    }

    public double getHeight() {
        return height;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }
}
