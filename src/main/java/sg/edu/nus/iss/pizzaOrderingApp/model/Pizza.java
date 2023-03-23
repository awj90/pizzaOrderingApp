package sg.edu.nus.iss.pizzaOrderingApp.model;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Pizza implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message="Please select a pizza")
    private String flavour;
    
    @NotBlank(message="Please select a pizza size")
    private String size;

    @Min(value=1, message="Minimum order quantity is 1")
    @Max(value=10, message="Maximum order quantity is 10")
    private int quantity;

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
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

}
