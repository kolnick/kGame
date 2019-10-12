package com.game.engine.common.util.system;

public class OSUtil {

    /**
     * 操作系统名
     */
    private static String os = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        // windows
        return (os.contains("win"));
    }

    public static boolean isMac() {
        // Mac
        return (os.contains("mac"));
    }

    public static boolean isUnix() {
        // linux or unix
        return (os.contains("nix") || os.contains("nux"));
    }

}

