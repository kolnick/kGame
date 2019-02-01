package com.game.util.system;

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
    
    public static boolean isSolaris() {
        // Solaris
        return (os.contains("sunos"));
    }
    
    public static void main(String[] args) {
        if (OSUtil.isWindows()) {
            System.out.println("This is Windows");
        } else if (OSUtil.isMac()) {
            System.out.println("This is Mac");
        } else if (OSUtil.isUnix()) {
            System.out.println("This is Unix or Linux");
        } else if (OSUtil.isSolaris()) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
        }
    }
    
}
