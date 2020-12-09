package com.bjtu.redis;
/**
        * @Author: 余福 18301143
        * @Date: 2020/12/9
        * @Description: 用户实体类
        */
public class user {
    public String username;
    public String  userno;
    public String intime;
    public String outtime;
    public user(String username1,String no,String intime1){
        username=username1;
        intime=intime1;
        userno=no;
    }
    public void Outtime(String outtime1){
        outtime=outtime1;
    }
    public String getUsername(){
        return username;
    }
    public String getIntime(){
        return userno;
    }
    public String getintime(){
        return intime;
    }
    public String getOuttime(){
        return outtime;
    }

}
