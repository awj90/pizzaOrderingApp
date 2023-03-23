package sg.edu.nus.iss.pizzaOrderingApp.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Confirmation implements Serializable {
    
    // private static final long serialVersionUID = 1L;

    private String orderId;
    private String name;
    private String address;
    private String phone;
    private boolean rush;
    private String comments;
    private String pizza;
    private String size;
    private int quantity;
    private float total;

    public Confirmation() {}

    public Confirmation(Pizza pizza, Order order) {
        this.orderId = order.getId();
        this.name = order.getName();
        this.address = order.getAddress();
        this.phone = order.getPhoneNumber();
        this.rush = order.isRush();
        this.comments = order.getComments();
        this.pizza = pizza.getFlavour();
        this.size = pizza.getSize();
        this.quantity = pizza.getQuantity();
        this.total = order.getTotalCost();
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                    .add("orderId", this.getOrderId())
                    .add("name", this.getName())
                    .add("address", this.getAddress())
                    .add("phone", this.getPhone())
                    .add("rush", String.valueOf(this.isRush()))
                    .add("comments", this.getComments())
                    .add("pizza", this.getPizza())
                    .add("size", this.getSize())
                    .add("quantity", String.valueOf(this.getQuantity()))
                    .add("total", String.valueOf(this.getTotal()))
                    .build();
    }

    public static Confirmation jsonStringToJavaObject(String json) throws IOException {
  
        Confirmation confirmation = new Confirmation();

        // InputStream is = new ByteArrayInputStream(json.getBytes());
        // InputStream is = new ByteArrayInputStream(json.getBytes());
        // JsonReader jsonReader = Json.createReader(is);
        StringReader sr = new StringReader(json);
        JsonReader jsonReader = Json.createReader(sr);
        JsonObject jsonObject = jsonReader.readObject();

        confirmation.setOrderId(jsonObject.getString("orderId"));
        confirmation.setName(jsonObject.getString("name"));
        confirmation.setAddress(jsonObject.getString("address"));
        confirmation.setPhone(jsonObject.getString("phone"));
        confirmation.setComments(jsonObject.getString("comments"));
        confirmation.setRush(Boolean.valueOf(jsonObject.getString("rush")));
        confirmation.setPizza(jsonObject.getString("pizza"));
        confirmation.setSize(jsonObject.getString("size"));
        confirmation.setQuantity(Integer.parseInt(jsonObject.getString("quantity")));
        confirmation.setTotal(Float.parseFloat(jsonObject.getString("total")));
    
        return confirmation;
    }
    
}
