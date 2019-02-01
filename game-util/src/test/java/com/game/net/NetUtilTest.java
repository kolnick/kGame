package com.game.net;

import com.game.util.network.NetUtil;
import org.junit.Test;

public class NetUtilTest {
    
    @Test
    public void testName() {
        String localAddress = NetUtil.getLocalHostName();
        System.out.println(localAddress);
    }
    
    @Test
    public void testAddress() {
        String localAddress = NetUtil.getLocalAddress();
        System.out.println(localAddress);
    }
    
    
}
