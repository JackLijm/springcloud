package com.example.redisdemo.normal;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisDemo {
    public static void main(String[] args) {
        //连接redis服务
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("连接成功");
        //查看服务是否运行
        //设置redis字符串数据
        jedis.set("runnoobkey","www.runoob.com");
        //设置一个列表
        jedis.lpush("site-list","Runoob");
        jedis.lpush("site-list","Google");
        jedis.lpush("site-list","Taobao");

        //获取存储数据并输出
        List<String> list = jedis.lrange("site-list",0,2);
        for(String s:list){
            System.out.println(s);
        }
      System.out.println("服务正在运行： " + jedis.ping());
    }
}
