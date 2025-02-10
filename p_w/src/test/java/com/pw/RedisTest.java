package com.pw;

import org.junit.jupiter.api.Test;

/***
 * @author cyd
 * @date 2023/8/1 15:17
 * @description <>
 **/
public class RedisTest {
    @Test
    public void testRedis(){
        //1 获取连接
//        Jedis jedis = new Jedis("1.92.159.74", 5009);
////        jedis.auth("123456");
//        jedis.select(0);
//        System.out.println(jedis.ping());
//
//        //2 执行具体的操作
//        jedis.set("username","xiaoming");
//
//        //输出打印
//        String value = jedis.get("username");
//        System.out.println(value);

        //删除操作
        //jedis.del("username");

//        //哈希存储
//        jedis.hset("myhash","addr","bj");
//        //输出打印
//        String hValue = jedis.hget("myhash", "addr");
//        System.out.println(hValue);
//
//        //keys * 输出打印
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }

        //3 关闭连接
//        jedis.close();
    }
}
