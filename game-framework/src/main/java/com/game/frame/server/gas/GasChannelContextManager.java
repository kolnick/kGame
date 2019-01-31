package com.game.frame.server.gas;

import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Map;

public class GasChannelContextManager {
    private final Map<String, ServerSocketChannel> serverChannelContextMap = new HashMap<>();

}
