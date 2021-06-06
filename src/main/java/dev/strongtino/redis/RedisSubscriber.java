package dev.strongtino.redis;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RedisSubscriber {

    private final RedisType type;
    private final Executor executor;

    void execute(RedisType type, RedisJsonObject object) {
        if (this.type == type) {
            executor.run(object);
        }
    }

    public interface Executor {

        void run(RedisJsonObject object);
    }
}
