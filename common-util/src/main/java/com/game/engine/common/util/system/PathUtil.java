package com.game.engine.common.util.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class PathUtil {

    private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);

    public static String getProjectRootPath() {
        return ClassLoader.getSystemResource("").getPath();
    }

    public static String getJarDir() {
        String path = getDirFromClassLoader();
        if (path == null) {
            path = System.getProperty("user.dir");
        }
        return path;
    }

    public static String getDirFromClassLoader() {
        try {
            String path = PathUtil.class.getName().replace(".", "/");
            path = "/" + path + ".class";
            URL url = PathUtil.class.getResource(path);
            String jarUrl = url.getPath();
            if (jarUrl.startsWith("file:")) {
                if (jarUrl.length() > 5) {
                    jarUrl = jarUrl.substring(5);
                }
                jarUrl = jarUrl.split("!")[0];
            } else {
                jarUrl = PathUtil.class.getResource("/").toString().substring(5);
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
     */
    public static File getConfigPathFileHolder(String customPath, String subDir) {
        File configPathFileHolder;
        if (customPath != null) {
            //1 获取自定义配置文件目录
            configPathFileHolder = new File(customPath);
        } else {

            //2获取JAR包所在位置配置文件
            configPathFileHolder = new File(PathUtil.getJarDir() + getFileSeparator() + subDir + getFileSeparator());
            if (!configPathFileHolder.exists()) {
                //3 获取工程资源包下文件
                String path = PathUtil.class.getResource("/").getPath() + subDir + "/";
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
}
