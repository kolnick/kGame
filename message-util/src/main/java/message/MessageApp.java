package message;

import com.game.util.file.FilePathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author caochaojie
 * 2018/12/15 16:48
 */
public class MessageApp {

    public static void main(String[] args) {
        // 读取xml 数据
        try {
            File configPath = FilePathUtil.getConfigPath("config.properties");
            Properties properties = new Properties();
            properties.load(new FileInputStream(configPath));
            String input = properties.getProperty("input");
            String output = properties.getProperty("output");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
