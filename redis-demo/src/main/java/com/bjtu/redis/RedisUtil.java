package com.bjtu.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author: 余福 18301143
 * @Date: 2020/12/9
 * @Description: Redis常用的操作都有封装
 */
public class RedisUtil {
    public final JedisPool yfjedisPool;
    public final Jedis yfjedis;

    //通过构造器 得到连接池返回一个Jedis资源
    public RedisUtil(){
        yfjedisPool=JedisInstance.getInstance();
        yfjedis=yfjedisPool.getResource();
    }
    //判断key是否存在
    public boolean exist(String key){
       /* if(yfjedis.exists(key)){
            return "1";
        }else{
            return "0";
        }*/
        return yfjedis.exists(key);
    }
     //设置key值
    public void set(String key,String value)
    {
        yfjedis.set(key,value);
    }
    //得到key值
    public String get(String key){
        return yfjedis.get(key);
    }
    //key值+1
    public void incr(String key){
        yfjedis.incr(key);
    }
    //key值-1
    public void decr(String key){
        yfjedis.decr(key);
    }
    //追加key内容
    public void append(String key,String value){
        yfjedis.append(key, value);
    }
    //返回key值长度
    public Long strlen(String key){
        return yfjedis.strlen(key);
    }
    //删除key值
    public void delete(String key){
        yfjedis.del(key);
    }

    //在list开头添加多个值
    public void lpush(String key,String value1){
        yfjedis.lpush(key,value1);
    }

    public void lrem(String key,String value,Long num){
        yfjedis.lrem(key,num,value);
    }

    public void rpush(String key,String value1){
        yfjedis.rpush(key,value1);
    }

    public void lrange(String key,Long start,Long end){
        yfjedis.lrange(key,start,end);
    }

    /*$redis->lpop($key); //移除并获取list的第一个元素
    $redis->rpop($key); //移除并获取list的最后一个元素
    $stop = $redis->llen($key) - 1; //获取list的长度
    $redis->lindex($key, $index);   //通过索引获取list元素*/


    public String lpop(String key){
        return yfjedis.lpop(key);
    }

    public String rpop(String key){
        return yfjedis.rpop(key);
    }
    public long llen(String key){
        return yfjedis.llen(key);
    }

    public String lindex(String key,Long value){
        return yfjedis.lindex(key,value);
    }

    /* //redis set 无序集合
    $redis->sadd($key, 'val1', 'val2'); //向集合中添加多个元素
    $redis->scard($key);    //获取集合元素个数
    $redis->spop($key); //移除并获取集合内随机一个元素
    $redis->srem($key, 'val1', 'val2'); //移除集合的多个元素
    $redis->sismember($key, 'val1');    //判断元素是否存在于集合内*/
    public void sadd(String key,String val1,String val2){
        yfjedis.sadd(key,val1,val2);
    }
    public Long scard(String key){
        return yfjedis.scard(key);
    }
    public void spop(String key){
        yfjedis.spop(key);
    }
    public void srem(String key,String val1,String val2){
        yfjedis.srem(key,val1,val2);
    }
    public boolean sismember(String key,String val1){
        return yfjedis.sismember(key,val1);
    }


    /*
    //redis sorted set 有序集合
    //有序集合里的元素都和一个分数score关联，就靠这个分数score对元素进行排序
    $redis->zadd($key, $score1, $val1, $score2, $val2); //向集合内添加多个元素
    $redis->zcard($key);    //获取集合内元素总数
    $redis->zcount($key, $minScore, $maxScore); //获取集合内分类范围内的元素
    $redis->zrem($key, $member1, $member2); //移除集合内多个元素*/

    public void zadd(String key,double score1,String val1){
        yfjedis.zadd(key,score1,val1);
    }

    public Long zcard(String key){
        return yfjedis.zcard(key);
    }
    public void zrem(String key,String  number){
        yfjedis.zrem(key,number);
    }

    public void hset(String name,String key,String value){
        yfjedis.hset(name,key,value);
    }
    public String hget(String name,String key){return yfjedis.hget(name,key);}
   /* #在name对应的hash中获取多个key的值

    #参数：
            #name:redis对应的name
        #keys,要获取key集合
        #*args,要获取的Key
    #如：
    hmget fle2 k1 k2 k3
    1) "3"
            2) "4"
            3) "5"*/
    public void hmget(String name,String key,String value){
         yfjedis.hmget(name,key, value);

    }
    public  void hdel(String name,String key1,String key2,String key3){
        yfjedis.hdel(name,key1,key2,key3);
    }


}

