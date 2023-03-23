package sg.edu.nus.iss.pizzaOrderingApp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.pizzaOrderingApp.model.Confirmation;
import sg.edu.nus.iss.pizzaOrderingApp.model.Order;
import sg.edu.nus.iss.pizzaOrderingApp.model.Pizza;
import sg.edu.nus.iss.pizzaOrderingApp.repository.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;
    
    private static final List<String> AVAILABLE_FLAVOURS = List.of("bella", "marinara", "spianatacalabrese", "margherita", "trioformaggio");
    private static final List<String> AVAILABLE_SIZES = List.of("sm", "md", "lg");

    public boolean isValidFlavour(Pizza pizza) {
        return AVAILABLE_FLAVOURS.contains(pizza.getFlavour());
    }

    public boolean isValidSize(Pizza pizza) {
        return AVAILABLE_SIZES.contains(pizza.getSize());
    }

    public float calculatePizzaCost(Pizza pizza) {
        
        float basePizzaCost = 0f;
        String flavour = pizza.getFlavour();
        switch(flavour) {
            case "bella", "marinara", "spianatacalabrese":
                basePizzaCost = 30f;
                break;
            case "margherita":
                basePizzaCost = 22f;
                break;
            case "trioformaggio":
                basePizzaCost = 25f;
                break;
            default:
                break;
        }

        float sizeMultiplier = 1f;
        String size = pizza.getSize();
        switch(size) {
            case "sm":
                sizeMultiplier = 1f;
                break;
            case "md":
                sizeMultiplier = 1.2f;
                break;
            case "lg":
                sizeMultiplier = 1.5f;
                break;
            default:
                break;
        }

        return basePizzaCost*sizeMultiplier*pizza.getQuantity();
    }

    public float calculateRushCost(Order order) {
        return order.isRush() ? 2f : 0f;
    }

    public void updateOrderCosts(Pizza pizza, Order order) {
        order.setPizzaCost(calculatePizzaCost(pizza));
        order.setRushCost(calculateRushCost(order));
        order.setTotalCost(order.getPizzaCost() + order.getRushCost());
    }

    public void savePizza(Pizza pizza, Order order) {
        Confirmation confirmation = new Confirmation(pizza, order);
        pizzaRepository.savePizza(confirmation);
    }

    public Optional<Confirmation> getDetailsFromDatabase(String orderId) throws IOException {
        return pizzaRepository.getDetailsFromDatabase(orderId);
    }
}
