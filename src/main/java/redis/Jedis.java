package redis;

import redis.clients.jedis.util.SafeEncoder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Jedis {
    public static void main(String[] args) {
        try {
            redis.clients.jedis.Jedis jedis =
                    new redis.clients.jedis.Jedis(new URI("redis://YOUR_REDIS_URI:YOUR_REDIS_PORT"));
            jedis.auth("YOUR_REDIS_PASSWORD");
            jedis.set("Cristiano", "Ronaldo");
            jedis.set("Lionel", "Messi");

            System.out.println(jedis.get("Cristiano"));
            System.out.println(jedis.dbSize());

            byte[] bigdata = new byte[1777];
            for (int b = 0; b < bigdata.length; b++) {
                bigdata[b] = (byte) ((byte) b % 255);
            }
            Map<String, String> hash = new HashMap<>();
            hash.put("data", SafeEncoder.encode(bigdata));

            System.out.println(jedis.hmset("foo", hash));
            Map<String, String> hash1 = jedis.hgetAll("foo");

            System.out.println(hash1.get("data"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
