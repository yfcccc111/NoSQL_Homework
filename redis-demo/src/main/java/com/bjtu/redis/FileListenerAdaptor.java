package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;

/**
 * @Author: 余福
 * @Date: 2020/12/9
 * @Description: 文件监听器类
 */
public class FileListenerAdaptor extends FileAlterationListenerAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(FileListenerAdaptor.class);

    /**
     * File changed Event.
     */
    @Override
    public void onFileChange(File file) {
        // TODO Auto-generated method stub
        super.onFileChange(file);
        //logger.info("文件改变事件");
        RedisDemoApplication.jsonchange();//文件改变时调用函数来改变counter
    }



    static final class FileFilterImpl implements FileFilter {

        /**
         * @return return true:返回所有目录下所有文件详细(包含所有子目录)
         * @return return false:返回主目录下所有文件详细(不包含所有子目录)
         */
        @Override
        public boolean accept(File file) {
            // TODO Auto-generated method stub
            //logger.info("文件路径: " + file);
            return true;
        }
    }

    public static void filelestenew( ) {
        try {

            // 构造观察类主要提供要观察的文件或目录，当然还有详细信息的filter
            FileAlterationObserver observer = new FileAlterationObserver("src/main/resources", new FileFilterImpl());
            // 构造收听类 没啥好说的
            FileListenerAdaptor listener = new FileListenerAdaptor();
            // 为观察对象添加收听对象
            observer.addListener(listener);
            // 配置Monitor，第一个参数单位是毫秒，是监听的间隔；第二个参数就是绑定我们之前的观察对象。
            FileAlterationMonitor fileMonitor = new FileAlterationMonitor(10000,
                    new FileAlterationObserver[] { observer });
            // 启动开始监听
            fileMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
