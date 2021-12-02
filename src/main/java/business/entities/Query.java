package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private int id;
    private List<Carport> carportList;

    private String status;
    private double price;
    private int carport_id;
    private String message;

    public Query(String status, double price, int carport_id, String message) {
        this.status = status;
        this.price = price;
        this.carport_id = carport_id;
        this.message = message;
        this.carportList = new ArrayList<>();
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

    public int getCarport_id() {
        return carport_id;
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
