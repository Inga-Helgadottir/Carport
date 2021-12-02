package business.entities;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String status;
    private Timestamp created;
    private double price;
    private String message;
    private int userId;
    private int queryId;
    private int carportId;

    public Order(double price, int userId, int queryId, int carportId) {
        this.price = price;
        this.userId = userId;
        this.queryId = queryId;
        this.carportId = carportId;
    }

    public Order(int id, double price, int userId, int queryId, int carportId) {
        this.id = id;
        this.price = price;
        this.userId = userId;
        this.queryId = queryId;
        this.carportId = carportId;
    }

    public Order(int id, String status, Timestamp created, double price, String message, int userId, int queryId, int carportId) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.price = price;
        this.message = message;
        this.userId = userId;
        this.queryId = queryId;
        this.carportId = carportId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    public int getCarportId() {
        return carportId;
    }

    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }
}
