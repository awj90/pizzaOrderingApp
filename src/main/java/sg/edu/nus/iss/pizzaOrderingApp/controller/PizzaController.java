package sg.edu.nus.iss.pizzaOrderingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaOrderingApp.model.Pizza;
import sg.edu.nus.iss.pizzaOrderingApp.service.PizzaService;
import sg.edu.nus.iss.pizzaOrderingApp.model.Order;

@Controller
public class PizzaController {
    
    @Autowired
    PizzaService pizzaService;

    @GetMapping(path="/")
    public String renderLandingPage(Model model, HttpSession session) {
        Pizza pizza = new Pizza();
        session.setAttribute("pizza", pizza);
        model.addAttribute("pizza", pizza);
        return "index";
    }

    @PostMapping(path="/pizza")
    public String postPizzaHandler(@ModelAttribute @Valid Pizza pizza, BindingResult bindingResult, Model model, HttpSession session) {
        
        if (!pizzaService.isValidFlavour(pizza)) {
            ObjectError flavourError = new ObjectError("globalError", "Invalid Pizza Flavour");
            bindingResult.addError(flavourError);
        }
        if (!pizzaService.isValidSize(pizza)) {
            ObjectError sizeError = new ObjectError("globalError", "Invalid Pizza Size");
            bindingResult.addError(sizeError);
        }
        if (bindingResult.hasErrors()) {
            return "index";
        } 
        
        Pizza p = (Pizza) session.getAttribute("pizza");
        p.setFlavour(pizza.getFlavour());
        p.setSize(pizza.getSize());
        p.setQuantity(pizza.getQuantity());
        session.setAttribute("pizza", p);

        model.addAttribute("order", new Order());
        return "view1";
    }

    @PostMapping(path="/pizza/order")
    public String postOrderHandler(@ModelAttribute @Valid Order order, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "view1";
        } else {
            Pizza p = (Pizza) session.getAttribute("pizza");
            
            pizzaService.updateOrderCosts(p, order);
            pizzaService.savePizza(p, order);
            session.setAttribute("order", order);
            model.addAttribute("order", order);
            return "view2";
        }
    }
}
