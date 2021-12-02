package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private String status;
    private double price;
    private int user_id;
    private String message;
//-------udenfor constructor---------------
    private int id;
    private List<Carport> carportList;

    public Query(String status, double price, int user_id, String message) {
        this.status = status;
        this.price = price;
        this.user_id = user_id;
        this.message = message;
        this.carportList = new ArrayList<>();
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
