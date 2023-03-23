package sg.edu.nus.iss.pizzaOrderingApp.repository;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzaOrderingApp.model.Confirmation;

@Repository
public class PizzaRepository {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    public void savePizza(Confirmation confirmation) {
        redisTemplate.opsForValue().set(confirmation.getOrderId(), confirmation.toJsonObject().toString());
    }

    public Optional<Confirmation> getDetailsFromDatabase(String orderId) throws IOException {
        String json = (String) redisTemplate.opsForValue().get(orderId);
        
        if((json==null || json.trim().length() <= 0)){
            return Optional.empty();
        }

        return Optional.of(Confirmation.jsonStringToJavaObject(json));
    }
}
