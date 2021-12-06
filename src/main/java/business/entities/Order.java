package business.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String status;
    private Timestamp created;
    private double price;
    private String message;
    private int query_id;
    private int user_id;
    //-------------udenfor constructor--------------
    private List<Carport> carportList;
    private int id;

    public Order(String status, Timestamp created, double price, String message, int query_id, int user_id) {
        this.status = status;
        this.created = created;
        this.price = price;
        this.message = message;
        this.query_id = query_id;
        this.user_id = user_id;
        this.carportList =  new ArrayList<>();
    }

    public Order(double price, int userId, int queryId) {
        this.price = price;
        this.user_id = userId;
        this.query_id = queryId;
    }

    public void setCarportList(List<Carport> carportList) {
        this.carportList = carportList;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public double getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public int getQuery_id() {
        return query_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
