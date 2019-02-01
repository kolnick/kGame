package com.game.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * 文件操作工具类
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/7/20.
 */
public class FilePathUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(FilePathUtil.class);
    public static final String CODE_UTF_8 = "utf8";
    public static final String CODE_GBK = "gbk";
    public static final String TYPE_TXT = "txt";
    public static final String TYPE_JAVA = "java";
    public static final String TYPE_CVS = "CVS";
    
    
    /**
     * syslog应用程序被打包成为jar包发布。在syslog服务中，需要在jar文件位置创建临时文件夹，以保存数据。
     * 临时文件夹：1 读文件中，ftp到中央日志服务的文件，被放到临时文件夹后再读。
     * 2 分析会后的日志，保存一个月。如果选择了备份，则把每天需要备份的文件移动到一个临时备份文件夹。
     * 逻辑：如果getDirFromClassLoader()方法计算不出来path，就取System.getProperty("user.dir")用户工作目录
     */
    public static String getJarDir() {
        String path = getDirFromClassLoader();
        if (path == null) {
            path = System.getProperty("user.dir");
        }
        return path;
    }
    
    /**
     * 从通过Class Loading计算路径：
     * 1 class文件通过jar包加载：
     * 如果为jar包，该包为d:/test/myProj.jar
     * 该方法返回d:/test这个目录（不受用户工作目录影响）
     * 提示：在jar包加载 的时候，通过指定加载FileUtil的class资源得到jar:<url>!/{entry}计算出加载路径
     * 2 class文件直接被加载：
     * 如果是web工程,class文件放在D:\tools\apache-tomcat-5.5.27\webapps\webProj\WEB-INF\classes
     * 该方法返回D:\tools\apache-tomcat-5.5.27\webapps\webProj\WEB-INF
     * 即返回放class文件夹的上一层目录。
     */
    private static String getDirFromClassLoader() {
        try {
            String path = FilePathUtil.class.getName().replace(".", "/");
            path = "/" + path + ".class";
            URL url = FilePathUtil.class.getResource(path);
            String jarUrl = url.getPath();
            if (jarUrl.startsWith("file:")) {
                if (jarUrl.length() > 5) {
                    jarUrl = jarUrl.substring(5);
                }
                jarUrl = jarUrl.split("!")[0];
                
            } else {
                jarUrl = FilePathUtil.class.getResource("/").toString().substring(5);
            }
            File file = new File(jarUrl);
            return file.getParent();
            
        } catch (Exception e) {
        }
        return null;
    }
    
    
    /**
     * 获取配置文件
     * 1首先判断是否输入了自定义配置文件地址
     * 2判断JAR包地址
     * 3判断工程编译环境地址
     *
     * @param customPath 默认配置文件路径
     * @return
     * @throws FileNotFoundException
     */
    public static File getConfigPathFileHolder(String customPath, String subDir) {
        File configPathFileHolder;
        if (customPath != null) {
            //1 获取自定义配置文件目录
            configPathFileHolder = new File(customPath);
//			Log.sys.debug("获取自定义配置文件:"+customPath);
        } else {
            
            //2获取JAR包所在位置配置文件
            configPathFileHolder = new File(FilePathUtil.getJarDir() + getFileSeparator() + subDir + getFileSeparator());
            if (!configPathFileHolder.exists()) {
                //3 获取工程资源包下文件
                String path = FilePathUtil.class.getResource("/").getPath() + subDir + "/";
                configPathFileHolder = new File(path);
            } else {
                logger.debug("获取JAR包所在位置配置文件:" + configPathFileHolder.getPath());
            }
        }
        return configPathFileHolder;
    }
    
    //获取文件分隔符
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }
    
    /**
     * 找到对应的资源文件
     *
     * @param subDir
     * @return
     * @throws FileNotFoundException
     */
    public static File getConfigPath(String subDir) {
        return getConfigPathFileHolder(null, subDir);
    }
    
    
    public static String getUserDirFile(String fileName) {
        return System.getProperty("user.dir") + File.separator + fileName;
    }
    
}