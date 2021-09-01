package dev.strongtino.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;

@RequiredArgsConstructor
public class RedisSynchronization extends JedisPubSub {

    private final RedisService service;

    @Override
    public void onMessage(String channel, String channelMessage) {
        if (!channel.equals(RedisService.CHANNEL)) return;

        String[] args = channelMessage.split(RedisService.SPLIT_CHAR);
        RedisType type = Arrays.stream(RedisType.values())
                .filter(redisType -> redisType.name().equals(args[0]))
                .findFirst()
                .orElse(null);

        if (type == null) return;

        service.getSubscribers().forEach(subscriber -> subscriber.execute(type, new RedisJsonObject(args[1])));
    }
}
