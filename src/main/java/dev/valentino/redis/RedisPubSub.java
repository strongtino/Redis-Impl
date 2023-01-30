package dev.valentino.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;

@RequiredArgsConstructor
public class RedisPubSub extends JedisPubSub {

    private final RedisService service;

    @Override
    public void onMessage(String channel, String channelMessage) {
        if (!channel.equals(RedisService.CHANNEL)) return;

        String[] args = channelMessage.split(RedisService.SPLIT_CHAR);
        Arrays.stream(RedisMessage.values())
                .filter(redisMessage -> redisMessage.name().equals(args[0]))
                .findFirst()
                .ifPresent(message -> service.getSubscribers().forEach(subscriber -> subscriber.execute(message, new RedisJsonObject(args[1]))));
    }
}
