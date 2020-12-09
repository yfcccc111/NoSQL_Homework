package com.bjtu.redis;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 余福  18301143
 * @Date: 2020/12/9
 * @Description: main函数操作
 */

/**
 *  SpringBootApplication
 * 用于代替 @SpringBootConfiguration（@Configuration）、 @EnableAutoConfiguration 、 @ComponentScan。
 * <p>
 * SpringBootConfiguration（Configuration） 注明为IoC容器的配置类，基于java config
 * EnableAutoConfiguration 借助@Import的帮助，将所有符合自动配置条件的bean定义加载到IoC容器
 * ComponentScan 自动扫描并加载符合条件的组件
 */
@SpringBootApplication
public class RedisDemoApplication {
    public static Counter[] counter;
    public static RedisUtil redisUtil;
    public  static ReadJson readJson;

    //文件监听器发现json文件修改时通过此操作来改变Counter
    public static  void jsonchange(){
        readJson.getCounters(counter);
    }

    public static void main(String[] args) throws ParseException {

       //SpringApplication.run(RedisDemoApplication.class, args);
        redisUtil=new RedisUtil();
        String rname="gypkjjpgg";
        String rID="42d4hou1";
        String ke="1iqlpist";
        String ke2="1kam3list";
        Room room=new Room(rname,rID,ke,ke2,redisUtil);

        boolean dd=true;
        long i=0;

       // Counter counter;
        readJson=new ReadJson();
        counter = new Counter[5];
        readJson.getCounters(counter);
       // System.out.println(counter[0].countername);
        FileListenerAdaptor.filelestenew();

        //之类通过while循环来等待用户操作
        while(dd){
            System.out.println("输入“1”进入直播间，输入“2”退出直播间");
            System.out.println("输入“3”查看一段时间里进入直播间的人数，输入“4”查看一段里退出直播间的人数");
            System.out.println("输入“5“直播结束");
            Scanner scanner=new Scanner(System.in);
            String s;
            s=scanner.nextLine();


            if(s.equals(counter[0].counteraction)){
                //System.out.println(counter[0].actiondesp);之类我用来看counter的值是否因为文件的修改而改变
                  counter[0].Action1(redisUtil,i,room);

                i++;
            }
            else if(s.equals(counter[1].counteraction)) {
                 counter[1].Action2(redisUtil,room);
            }
            else if(s.equals(counter[2].counteraction)){
                counter[2].Action3(redisUtil,room);
            }else if(s.equals(counter[3].counteraction)){
                counter[3].Action4(redisUtil,room);
            }else if(s.equals("5")){
                break;
            }
            System.out.println("直播间人数"+redisUtil.get(rname));

        }

      //删除数据
       /* while (dd){
           String s= redisUtil.lpop(rID);
           if(s.equals(null)){
               break;
           }
            redisUtil.hdel(s,"username","intime","outtime");
        }*/

   }
}

/*

总结：

1、获取运行环境信息和回调接口。例如ApplicationContextIntializer、ApplicationListener。
完成后，通知所有SpringApplicationRunListener执行started()。

2、创建并准备Environment。
完成后，通知所有SpringApplicationRunListener执行environmentPrepared()

3、创建并初始化 ApplicationContext 。例如，设置 Environment、加载配置等
完成后，通知所有SpringApplicationRunListener执行contextPrepared()、contextLoaded()

4、执行 ApplicationContext 的 refresh，完成程序启动
完成后，遍历执行 CommanadLineRunner、通知SpringApplicationRunListener 执行 finished()

参考：
https://blog.csdn.net/zxzzxzzxz123/article/details/69941910
https://www.cnblogs.com/shamo89/p/8184960.html
https://www.cnblogs.com/trgl/p/7353782.html

分析：

1） 创建一个SpringApplication对象实例，然后调用这个创建好的SpringApplication的实例方法

public static ConfigurableApplicationContext run(Object source, String... args)

public static ConfigurableApplicationContext run(Object[] sources, String[] args)

2） SpringApplication实例初始化完成并且完成设置后，就开始执行run方法的逻辑了，
方法执行伊始，首先遍历执行所有通过SpringFactoriesLoader可以查找到并加载的
SpringApplicationRunListener，调用它们的started()方法。


public SpringApplication(Object... sources)

private final Set<Object> sources = new LinkedHashSet<Object>();

private Banner.Mode bannerMode = Banner.Mode.CONSOLE;

...

private void initialize(Object[] sources)

3） 创建并配置当前SpringBoot应用将要使用的Environment（包括配置要使用的PropertySource以及Profile）。

private boolean deduceWebEnvironment()

4） 遍历调用所有SpringApplicationRunListener的environmentPrepared()的方法，通知Environment准备完毕。

5） 如果SpringApplication的showBanner属性被设置为true，则打印banner。

6） 根据用户是否明确设置了applicationContextClass类型以及初始化阶段的推断结果，
决定该为当前SpringBoot应用创建什么类型的ApplicationContext并创建完成，
然后根据条件决定是否添加ShutdownHook，决定是否使用自定义的BeanNameGenerator，
决定是否使用自定义的ResourceLoader，当然，最重要的，
将之前准备好的Environment设置给创建好的ApplicationContext使用。

7） ApplicationContext创建好之后，SpringApplication会再次借助Spring-FactoriesLoader，
查找并加载classpath中所有可用的ApplicationContext-Initializer，
然后遍历调用这些ApplicationContextInitializer的initialize（applicationContext）方法
来对已经创建好的ApplicationContext进行进一步的处理。

8） 遍历调用所有SpringApplicationRunListener的contextPrepared()方法。

9） 最核心的一步，将之前通过@EnableAutoConfiguration获取的所有配置以及其他形式的
IoC容器配置加载到已经准备完毕的ApplicationContext。

10） 遍历调用所有SpringApplicationRunListener的contextLoaded()方法。

11） 调用ApplicationContext的refresh()方法，完成IoC容器可用的最后一道工序。

12） 查找当前ApplicationContext中是否注册有CommandLineRunner，如果有，则遍历执行它们。

13） 正常情况下，遍历执行SpringApplicationRunListener的finished()方法、
（如果整个过程出现异常，则依然调用所有SpringApplicationRunListener的finished()方法，
只不过这种情况下会将异常信息一并传入处理）


private <T> Collection<? extends T> getSpringFactoriesInstances(Class<T> type)

private <T> Collection<? extends T> getSpringFactoriesInstances(Class<T> type,
			Class<?>[] parameterTypes, Object... args)

public void setInitializers

private Class<?> deduceMainApplicationClass()

public ConfigurableApplicationContext run(String... args)

private void configureHeadlessProperty()

private SpringApplicationRunListeners getRunListeners(String[] args)

public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader)


*/
