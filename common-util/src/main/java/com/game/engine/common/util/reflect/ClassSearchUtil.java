package com.game.engine.common.util.reflect;

import com.ym.server.engine.common.meta.SplitSymbolConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类搜索工具
 */
public class ClassSearchUtil {
    private static final String PROTOCOL_FILE = "file";
    private static final String PROTOCOL_JAR = "jar";
    private static final String END_CLASS = ".class";

    private static Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);


    public static List<Class> getAllClassByInterface(Class clazz) {
        return getAllClassByInterface(clazz, clazz.getPackage().getName());
    }

    public static List<Class> getAllClassByInterface(Class clazz, String packageName) {
        List<Class> list = new ArrayList<>();
        Set<Class<?>> allClass;
        if (clazz.isInterface()) {
            try {
                allClass = getAllClass(packageName);
                // 除接口和抽象类之外的所有类
                isAssignableFrom(clazz, list, allClass);
            } catch (Exception var5) {
                System.out.println("出现异常");
            }
        } else {
            try {
                // 只获取实现这个
                allClass = getAllClass(packageName);
                isAssignableFrom(clazz, list, allClass);
            } catch (Exception var4) {
                System.out.println("出现异常");
            }
        }

        return list;
    }

    /**
     * @param clazz
     * @param list
     * @param allClass
     */
    public static void isAssignableFrom(Class clazz, List<Class> list, Set<Class<?>> allClass) {

        for (Class<?> aClass : allClass) {
            if (clazz.isAssignableFrom(aClass) && !clazz.equals(aClass)) {
                list.add(aClass);
            }
        }
    }

    public static Set<Class<?>> getAllClass(String packageName) {
        Set<Class<?>> allClazz = new LinkedHashSet<>();
        boolean recursive = true;
        String packageDir = packageName.replace(".", "/");
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if (PROTOCOL_FILE.equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    allClazz.addAll(findClassFromDir(packageName, filePath, recursive));
                } else if (PROTOCOL_JAR.equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    allClazz.addAll(findClassFromJar(jar, packageDir));
                }
            }
        } catch (Throwable var8) {
            LOGGER.error("读取日志Class文件出错", var8);
        }

        return allClazz;
    }

    public static Set<Class<?>> findClassWithAnnotaion(String packageName, Class<? extends Annotation> annotationClass) {
        Set<Class<?>> allClass = getAllClass(packageName);
        Set<Class<?>> ret = new LinkedHashSet<>();
        allClass.stream().filter((v) -> v.getAnnotation(annotationClass) != null).forEach(ret::add);
        return ret;
    }

    private static Set<Class<?>> findClassFromJar(JarFile jar, String packageDir) {
        Set<Class<?>> ret = new LinkedHashSet<>();
        Enumeration entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = (JarEntry) entries.nextElement();
            if (!entry.isDirectory()) {
                String name = entry.getName();
                if (name.startsWith(packageDir) && name.endsWith(END_CLASS)) {
                    name = name.replaceAll(SplitSymbolConst.XIEXIAN, SplitSymbolConst.DIAN);
                    name = name.substring(0, name.length() - 6);

                    try {
                        Class<?> clazz = Class.forName(name, false, ClassUtil.class.getClassLoader());
                        ret.add(clazz);
                    } catch (Throwable var7) {
                        LOGGER.error("读取Jar中的Class文件出错:" + name, var7);
                    }
                }
            }
        }

        return ret;
    }

    private static Set<Class<?>> findClassFromDir(String packageName, String filePath, boolean recursive) {
        File dir = new File(filePath);
        if (dir.exists() && dir.isDirectory()) {
            Set<Class<?>> ret = new LinkedHashSet<>();
            File[] files = dir.listFiles((filex) -> recursive && filex.isDirectory() || filex.getName().endsWith(END_CLASS));
            for (File file : Objects.requireNonNull(files)) {
                if (file.isDirectory()) {
                    ret.addAll(findClassFromDir(packageName + "." + file.getName(), file.getAbsolutePath(), recursive));
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);

                    try {
                        Class<?> clazz = Class.forName(packageName + '.' + className, false, ClassUtil.class.getClassLoader());
                        ret.add(clazz);
                    } catch (Throwable var11) {
                        LOGGER.error("读取文件夹中的Class文件出错", var11);
                    }
                }
            }

            return ret;
        } else {
            return Collections.emptySet();
        }
    }
}
