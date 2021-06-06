package dev.strongtino.redis;

import com.google.gson.JsonObject;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Getter
public class RedisService {

    private final JedisPool subscriberPool, publisherPool;

    final List<RedisSubscriber> subscribers = new ArrayList<>();

    static final String SPLIT_CHAR = "<-.->";
    static final String CHANNEL = "channel";

    public RedisService(String address, int port) {
        subscriberPool = publisherPool = new JedisPool(address, port);

        Executors.newSingleThreadExecutor().execute(() -> {
            try (Jedis jedis = subscriberPool.getResource()) {
                jedis.subscribe(new RedisSynchronization(this), CHANNEL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void subscribe(RedisType type, RedisSubscriber.Executor executor) {
        subscribers.add(new RedisSubscriber(type, executor));
    }

    public void publish(RedisType type, JsonObject object) {
        try (Jedis jedis = publisherPool.getResource()) {
            try {
                jedis.publish(CHANNEL, type.name() + SPLIT_CHAR + object.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
