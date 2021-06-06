package dev.strongtino.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

@RequiredArgsConstructor
public class RedisSynchronization extends JedisPubSub {

    private final RedisService redisService;

    @Override
    public void onMessage(String channel, String channelMessage) {
        if (!channel.equals(RedisService.CHANNEL)) return;

        String[] args = channelMessage.split(RedisService.SPLIT_CHAR);
        RedisType type = RedisType.get(args[0]);

        if (type == null) return;

        redisService.getSubscribers().forEach(hook -> hook.execute(type, new RedisJsonObject(args[1])));
    }
}
