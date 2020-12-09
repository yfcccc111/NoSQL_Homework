package com.bjtu.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author: 余福
 * @Date: 2020/12/9
 * @Description: Coutenr类
 */

public class Counter {
    public String countername;
    public String actiondesp;
    public String counteraction;
    //public String key;

    //给Counter类的变量赋值，通过这些值可以知道执行了哪些操作
    public  Counter(JSONObject j)  {
            countername=j.getString("countername");
            actiondesp=j.getString("actiondesp");
            counteraction=j.getString("counteraction");
           // key=j.getString("Key");
    }

    public  String getCountername(){
        return countername;
    }

    //进入直播间的操作
    public void Action1(RedisUtil redisUtil,Long i,Room room){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

        String hehe = dateFormat.format( now );

        System.out.println("输入用户名");
        Scanner scanner2=new Scanner(System.in);
        String s2;
        s2=scanner2.nextLine();
        user user=new user(s2,String.valueOf(i),hehe);
        room.inroom(redisUtil,user);
        System.out.print(user.username);

        System.out.print(redisUtil.hget(user.userno,"intime")+"用户编号 "+redisUtil.lindex(room.roomID,i)+" 进入直播间，");
        i++;
    }

    //出直播间的操作
    public void Action2(RedisUtil redisUtil,Room room){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

        String hehe = dateFormat.format( now );

        System.out.println("输入退出直播间的用户编号");
        Scanner scanner1=new Scanner(System.in);
        String s1;
        s1=scanner1.nextLine();
        room.outroom(redisUtil, Long.valueOf(s1),hehe);
        System.out.print(redisUtil.hget(s1,"outtime")+redisUtil.hget(s1,"username")+"用户编号 "+redisUtil.lindex(room.roomID,Long.valueOf(s1))+" 退出直播间,");
    }

    //查一段时间里进入直播间的人数
    public void Action3(RedisUtil redisUtil,Room room){
        System.out.println("手动输入时间段");
        System.out.println("时间段头");
        Scanner scanner2=new Scanner(System.in);
        String s2=null;
        s2=scanner2.nextLine();
        String ktime=s2;
        System.out.println("时间段尾");
        Scanner scanner6=new Scanner(System.in);
        String s6 = null;
        s6=scanner6.nextLine();
        String jtime=s6;
        System.out.println(ktime+"到"+jtime);
        int p=0;
        if(true)
        {
            boolean hh=true;
            long yk=0;

            while(hh) {
                if(yk>(redisUtil.llen(room.inkey)-1)){
                    break;
                }
                String time=redisUtil.lindex(room.inkey,yk);

                int y1=0;
                y1=time.compareTo(ktime);
                int y2=0;
                y2=time.compareTo(jtime);
                System.out.println(time);
                System.out.println(y1+"和"+y2);
                if(y2>0){
                    break;
                }
                if(y1>0&&y2<0){
                    p++;
                }

                yk++;
            }
        }/*else if(s2.equals("0")){
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

            String hehe = dateFormat.format( now );

        }*/

        System.out.println(ktime+"到"+jtime+"期间进入直播间的人数为"+p);
    }

    //查询一段时间里出直播间的人数
    public void Action4(RedisUtil redisUtil,Room room){
        System.out.println("手动输入时间段");
        System.out.println("时间段头");
        Scanner scanner2=new Scanner(System.in);
        String s2=null;
        s2=scanner2.nextLine();
        String ktime=s2;
        System.out.println("时间段尾");
        Scanner scanner6=new Scanner(System.in);
        String s6 = null;
        s6=scanner6.nextLine();
        String jtime=s6;
        System.out.println(ktime+"到"+jtime);
        int p=0;
        if(true)
        {
            boolean hh=true;
            long yk=0;

            while(hh) {
                if(yk>(redisUtil.llen(room.outkey)-1)){
                    break;
                }
                String time=redisUtil.lindex(room.outkey,yk);

                int y1=0;
                y1=time.compareTo(ktime);
                int y2=0;
                y2=time.compareTo(jtime);
                System.out.println(time);
                System.out.println(y1+"和"+y2);
                if(y2>0){
                    break;
                }
                if(y1>0&&y2<0){
                    p++;
                }

                yk++;
            }

        }
        System.out.println(ktime+"到"+jtime+"期间退出直播间的人数为"+p);
    }

}
