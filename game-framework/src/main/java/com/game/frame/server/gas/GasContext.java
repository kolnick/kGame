package com.game.frame.server.gas;

import com.game.frame.server.core.constant.ServerContext;
import com.game.util.service.Service;

public abstract class GasContext implements ServerContext, Service {

    private String serverName;
    private String serverId;

    @Override
    public String getServerName() {
        return this.serverName;
    }

    @Override
    public String getServerId() {
        return this.serverId;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
