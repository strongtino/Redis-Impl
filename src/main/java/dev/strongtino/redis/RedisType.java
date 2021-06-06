package dev.strongtino.redis;

import java.util.Arrays;

public enum RedisType {

    HELLO_WORLD;

    public static RedisType get(String name) {
        return Arrays.stream(RedisType.values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
