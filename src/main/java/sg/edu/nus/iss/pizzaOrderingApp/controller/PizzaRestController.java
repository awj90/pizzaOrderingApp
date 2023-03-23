package sg.edu.nus.iss.pizzaOrderingApp.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import sg.edu.nus.iss.pizzaOrderingApp.model.Confirmation;
import sg.edu.nus.iss.pizzaOrderingApp.service.PizzaService;

@RestController
@RequestMapping(path="/order", produces="application/json")
public class PizzaRestController {
    
    @Autowired 
    PizzaService pizzaService;

    @GetMapping(path="/{orderId}", produces="application/json")
    public ResponseEntity<String> getDetailsFromDatabase(@PathVariable String orderId) throws IOException {
        Optional<Confirmation> c = pizzaService.getDetailsFromDatabase(orderId);
        if (c.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("message", "Order %s not found".formatted(orderId))
                                            .build()
                                            .toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(c.get().toJsonObject().toString());
        }
    }


}
