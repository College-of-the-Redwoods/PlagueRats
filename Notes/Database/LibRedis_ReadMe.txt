Integrating LibGDX with Redis can be useful for storing scores, game states, or managing multiplayer data. Since Redis is not part of LibGDX directly, we need to use a Java Redis client like Jedis for example. 

If you're using Gradle (which we are I believe) we can add Jedis to our build.gradle file:

dependencies {
    implementation 'redis.clients:jedis:5.1.0' // or latest version
}

In our LibGDX game (typically in a class like MainGame), we can create a Redis connection like this:

import redis.clients.jedis.Jedis;

public class RedisManager {
    private Jedis jedis;

    public RedisManager(String host, int port) {
        jedis = new Jedis(host, port);
    }

    public void setScore(String username, int score) {
        jedis.set("score:" + username, String.valueOf(score));
    }

    public int getScore(String username) {
        String score = jedis.get("score:" + username);
        return score != null ? Integer.parseInt(score) : 0;
    }

    public void close() {
        jedis.close();
    }
}

LibGDX runs on multiple platforms (desktop, Android, iOS, HTML), but Jedis only works on desktop. If we want to use Android or iOS for example, we need an alternative approache or bridge it through an API.