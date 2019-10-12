package com.game.engine.common.util.network;

import com.ym.server.engine.common.util.system.OSUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * IP工具类
 */
public class IPUtil {
    /**
     * 获取本地IP地址
     *
     * @return
     * @throws UnknownHostException
     * @throws SocketException
     */
    public static String getLocalIp() throws UnknownHostException, SocketException {
        if (OSUtil.isWindows()) {
            return InetAddress.getLocalHost().getHostAddress();
        }
        return getLinuxLocalIp();
    }


    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface networkInterface = en.nextElement();
            String name = networkInterface.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddress = networkInterface.getInetAddresses(); enumIpAddress.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddress.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipAddress = inetAddress.getHostAddress();
                        if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                            ip = ipAddress;
                        }
                    }
                }
            }
        }
        return ip;
    }
}
