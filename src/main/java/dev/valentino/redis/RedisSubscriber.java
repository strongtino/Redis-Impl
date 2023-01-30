package dev.valentino.redis;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RedisSubscriber {

    private final RedisMessage type;
    private final Executor executor;

    void execute(RedisMessage type, RedisJsonObject object) {
        if (this.type == type) {
            executor.run(object);
        }
    }

    public interface Executor {
        void run(RedisJsonObject object);
    }
}
