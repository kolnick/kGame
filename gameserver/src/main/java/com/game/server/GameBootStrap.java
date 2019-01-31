package com.game.server;

import com.game.server.config.ServerConfig;
import lombok.extern.log4j.Log4j;

import java.io.File;

@Log4j
public class GameBootStrap {
    public static void start(String[] args) {
        String configPath;
        if (args.length > 0) {
            configPath = args[0];
        } else {
            configPath = new File("conf/dev/config.properties").getPath();
        }
        System.out.println("---------------------" + configPath);
        ServerOption option = new ServerOption();
        option.build(configPath);
        String osName = System.getProperties().getProperty("os.name");
        log.info("服务器运行在 : " + osName);
        log.info("服务器当前版本号 :" + ServerConfig.VERSION);
        GameServer gameServer = new GameServer(option);
        gameServer.start();
    }

    public static void stop() {

    }

    public static void restart() {

    }
}
