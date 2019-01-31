package com.game.server;

import com.game.config.ConfigDataManager;
import com.game.pub.basic.ModelManager;
import com.game.server.config.ServerConfig;
import lombok.extern.log4j.Log4j;

@Log4j
public class GameServer extends Thread {
    public GameServer(ServerOption option) {

    }

    @Override
    public void run() {
        log.info("GameServer start");
        ConfigDataManager.getInstance().init(ServerConfig.CONFIG_DATA_PATH);

        ModelManager.getInstance().start();
    }

}
