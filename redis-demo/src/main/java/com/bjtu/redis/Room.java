package com.bjtu.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 余福 18301143
 * @Date: 2020/12/9
 * @Description: 直播房间实体类
 */
public class Room {
    public String roomname;
    public String roomID;
    public String startTime;
    public String endTime;
    public String inkey;
    public String outkey;
    public Room(String key,String key2,String key3,String key4,RedisUtil redisUtil){
        roomname=key;
        roomID=key2;
        inkey=key3;
        outkey=key4;

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

        String hehe = dateFormat.format( now );
        //redisUtil.rpush(inkey,hehe);

    }
    public void Createroom(RedisUtil redisUtil){
        redisUtil.set(roomname,"0");
    }
    public void inroom(RedisUtil redisUtil,user user){
        redisUtil.incr(roomname);
        redisUtil.rpush(roomID,user.userno);

        redisUtil.rpush(inkey,user.intime);
        redisUtil.hset(user.userno,"username",user.username);
        redisUtil.hset(user.userno,"intime",user.intime);

    }
    public void outroom(RedisUtil redisUtil,long num,String outtime){
        redisUtil.decr(roomname);
        //redisUtil.lrem(roomname,user.userno,num);
        //System.out.println(redisUtil.lindex(roomname,num));
        redisUtil.hset( String.valueOf(num),"outtime",outtime);

        redisUtil.rpush(outkey,outtime);
    }
}
