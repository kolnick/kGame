package com.game.util.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {
    
    public static String getLocalHostName() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getLocalAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
