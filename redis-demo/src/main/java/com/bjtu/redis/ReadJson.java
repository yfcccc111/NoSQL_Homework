package com.bjtu.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
/**
 * @Author: 余福 18301143
 * @Date: 2020/12/9
 * @Description: 读json文件类
 */

import java.io.*;




public class ReadJson {


    public ReadJson(){}

    public static String ReadJson(String filename)  {
        //存储文件中的数据
        String jsonStr = "";
        try {
            File jsonFile = new File(filename);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            //一个字符一个字符的读取文件
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            //关闭资源
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            //System.out.println("输入“1”进入直播间，输入“2”退出直播间,输入”3“退出");
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("输入“1”进入直播间，输入");
            return null;
        }
    }

    //得到counter的键值对

    public void getCounters(Counter []counters)  {
        //文件位置

        //文件位置
        String path = "src/main/resources/Counters.json";
        String s = ReadJson(path);
        JSONObject j = JSON.parseObject(s);
        JSONArray ja = j.getJSONArray("counters");
       // Map<String,Object> counters = new HashMap<>();
        int y=0;
        for (int i = 0; i < ja.size(); i++) {
            JSONObject key1 = (JSONObject)ja.get(i);

            Counter counter = new Counter(key1);
            counters[y]=counter;
            y++;
            //System.out.println(counter.getCountername());

        }
    }
}
