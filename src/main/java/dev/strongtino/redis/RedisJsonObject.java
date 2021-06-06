package dev.strongtino.redis;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class RedisJsonObject {

    private JsonObject object;

    public RedisJsonObject(String json) {
        object = JsonParser.parseString(json).getAsJsonObject();
    }

    public RedisJsonObject(JsonObject object) {
        this.object = object;
    }

    public String getString(String element) {
        return isFound(element) ? get(element).getAsString() : "";
    }

    public UUID getUUID(String element) {
        return isFound(element) ? UUID.fromString(get(element).getAsString()) : UUID.randomUUID();
    }

    public boolean getBoolean(String element) {
        return isFound(element) && get(element).getAsBoolean();
    }

    public int getInteger(String element) {
        return isFound(element) ? get(element).getAsInt() : -1;
    }

    public double getDouble(String element) {
        return isFound(element) ? get(element).getAsDouble() : -1;
    }

    public float getFloat(String element) {
        return isFound(element) ? get(element).getAsFloat() : -1;
    }

    public long getLong(String element) {
        return isFound(element) ? get(element).getAsLong() : -1;
    }

    public JsonObject getJsonObject(String element) {
        return isFound(element) ? get(element).getAsJsonObject() : new JsonObject();
    }

    private boolean isFound(String element) {
        return object != null && get(element) != null && !get(element).isJsonNull();
    }

    private JsonElement get(String element) {
        return object.get(element);
    }
}

