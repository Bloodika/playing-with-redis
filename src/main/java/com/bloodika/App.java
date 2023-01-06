package com.bloodika;

import redis.clients.jedis.JedisPool;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class App 
{
    public static void main( String[] args )
    {
        try(JedisPool jedisPool  = new JedisPool("<host>",6379);Jedis jedis = jedisPool.getResource()){
            System.out.println(jedis.isConnected());
            jedis.set("bloodika","active");
            jedis.set("dom", "not active");
            listAll(jedis);
        }
    }

    private static void listAll(final Jedis jedis){
        final Set<String> keys = jedis.keys("*");
        keys.forEach(key -> System.out.println(key + ":" + jedis.get(key)));
    }

}
