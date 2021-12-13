package business.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Query {
    private String status;
    private double price;
    private int user_id;
    private String message;
    private Timestamp created;
//-------udenfor constructor---------------
    private int id;
    private List<Carport> carportList;//only used for standard carport
    private List<Material> BOM;
    private Carport carport;
    private User user;
    private String type;
    private double coverage_ratio;
    private double salesPrice;


    public Query(String status, double price, int user_id, String message, Timestamp created) {
        this.status = status;
        this.price = price;
        this.user_id = user_id;
        this.message = message;
        this.carportList = new ArrayList<>();
        this.BOM = new ArrayList<>();
        this.created = created;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getCoverage_ratio() {
        return coverage_ratio;
    }

    public void setCoverage_ratio(double coverage_ratio) {
        this.coverage_ratio = coverage_ratio;
    }

    public Timestamp getCreated() {
        return created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public List<Material> getBOM() {
        return BOM;
    }

    public void setBOM(List<Material> BOM) {
        this.BOM = BOM;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getId() {
        return id;
    }

    public List<Carport> getCarportList() {
        return carportList;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarportList(List<Carport> carportList) {
        this.carportList = carportList;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
