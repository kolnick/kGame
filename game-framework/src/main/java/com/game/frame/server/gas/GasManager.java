package com.game.frame.server.gas;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

public class GasManager extends GasContext {
    private ServerBootstrap bootstrap;

    private int port;

    private int state;

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private void init(String w) {

    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return super.stop();
    }
}
