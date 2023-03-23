package sg.edu.nus.iss.pizzaOrderingApp.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Size(min=3, message="Name should at least be 3 characters")
    @NotBlank(message="Please enter your name")
    private String name;

    @NotBlank(message="Please enter your address")
    private String address;

    @Pattern(regexp="^[0-9]{8,}$", message="Phone number should at least be 8 digits")
    @NotBlank(message="Please enter your phone number")
    private String phoneNumber;

    private boolean rush = false;
    private String comments;

    private String id;

    private float pizzaCost;
    private float rushCost;
    private float totalCost;

    public Order() {
        this.id = UUID.randomUUID().toString().substring(0,8);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public float getPizzaCost() {
        return pizzaCost;
    }
    public void setPizzaCost(float pizzaCost) {
        this.pizzaCost = pizzaCost;
    }
    public float getRushCost() {
        return rushCost;
    }
    public void setRushCost(float rushCost) {
        this.rushCost = rushCost;
    }

    public float getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    
}
