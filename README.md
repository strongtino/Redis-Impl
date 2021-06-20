# Redis-Impl
Simplified redis publish-subscribe implementation that allows you to easily subscribe to and publish various messages accross multiple applications.

### Implementation Example
```java
public static void main(String[] args) {
    RedisService service = new RedisService("localhost", 6379);

    // Subscribing to the HELLO_WORLD messages
    service.subscribe(RedisType.HELLO_WORLD, object -> System.out.println("Hello World, " + object.getString("name") + '!'));

    // Publishing a HELLO_WORLD message
    JsonObject object = new JsonObject();
    object.addProperty("name", "strongtino");

    service.publish(RedisType.HELLO_WORLD, object);
}
```
