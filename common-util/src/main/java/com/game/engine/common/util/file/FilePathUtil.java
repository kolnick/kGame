package com.game.engine.common.util.file;

import java.io.File;

/**
 * 文件路径工具类
 *
 * @author caochaojie
 * @date 2019/08/28
 */
public class FilePathUtil {

    public static File getConfigPath(String subDir) {
        String path = System.getProperty("user.dir") + File.separator + subDir;
        File configFile = new File(path);
        if (!configFile.exists()) {
            path = FileUtil.class.getResource("/").getPath() + subDir;
            configFile = new File(path);
        }
        return configFile;
    }
}
