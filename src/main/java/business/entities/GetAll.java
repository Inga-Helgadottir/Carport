package business.entities;

import java.sql.Timestamp;

public class GetAll {
    private String status;
    private String msg;
    private Timestamp created;
    private double totalPrice;
    private String userName;
    private String userEmail;
    private int userPhone;
    private int quantity;
    private int carportId;
    private String carportType;
    private int singlePrice;
    private String carportName;
    private int length;
    private int width;
    private int height;
    private int roofAngle;
    private int shedLength;

    public GetAll(String status, String msg, Timestamp created, double totalPrice, String userName, String userEmail, int userPhone, int quantity, int carportId, String carportType, int singlePrice, String carportName, int length, int width, int height, int roofAngle, int shedLength, int shedWidth) {
        this.status = status;
        this.msg = msg;
        this.created = created;
        this.totalPrice = totalPrice;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.quantity = quantity;
        this.carportId = carportId;
        this.carportType = carportType;
        this.singlePrice = singlePrice;
        this.carportName = carportName;
        this.length = length;
        this.width = width;
        this.height = height;
        this.roofAngle = roofAngle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCarportId() {
        return carportId;
    }

    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }

    public String getCarportType() {
        return carportType;
    }

    public void setCarportType(String carportType) {
        this.carportType = carportType;
    }

    public int getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getCarportName() {
        return carportName;
    }

    public void setCarportName(String carportName) {
        this.carportName = carportName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public void setRoofAngle(int roofAngle) {
        this.roofAngle = roofAngle;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    int shedWidth;

}
