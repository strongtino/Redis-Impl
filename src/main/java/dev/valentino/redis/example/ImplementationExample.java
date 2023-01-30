package dev.valentino.redis.example;

import com.google.gson.JsonObject;
import dev.valentino.redis.RedisService;
import dev.valentino.redis.RedisMessage;

public class ImplementationExample {

    public static void main(String[] args) {
        RedisService service = new RedisService("localhost", 6379);

        // Subscribing to the HELLO_WORLD messages
        service.subscribe(RedisMessage.HELLO_WORLD, object -> System.out.println("Hello World, " + object.getString("name") + '!'));

        // Publishing a HELLO_WORLD message
        JsonObject object = new JsonObject();
        object.addProperty("name", "strongtino");

        service.publish(RedisMessage.HELLO_WORLD, object);
    }
}
