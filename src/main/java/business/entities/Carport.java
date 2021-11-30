package business.entities;

public class Carport {
    private int length;
    private int width;
    private int height;
    private int roof_angle;
    private int shed_length;
    private int shed_width;
    private String name;
    private double price;
    private String type;
    private String info;

    private int id;
    private String svg;
    private int quantity;

    public Carport(int length, int width, int height, int roof_angle, int shed_length, int shed_width, String name, String type, double price, String info) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.roof_angle = roof_angle;
        this.shed_length = shed_length;
        this.shed_width = shed_width;
        this.name = name;
        this.type = type;
        this.price = price;
        this.info = info;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoof_angle() {
        return roof_angle;
    }

    public int getShed_length() {
        return shed_length;
    }

    public int getShed_width() {
        return shed_width;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public int getId() {
        return id;
    }

    public String getSvg() {
        return svg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}