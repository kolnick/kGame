package com.game.util.file;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.game.util.string.StringUtil;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/4/27
 * @since 1.0.0
 */
public class FileUtil {
    
    public static String getFileType(String filePath) throws Exception {
        int indexOf = filePath.lastIndexOf(".");
        if (indexOf == -1 || indexOf == 0) {
            throw new Exception("测试");
        }
        return filePath.substring(indexOf, filePath.length());
    }
    
    public static String getProjectRootPath() {
        return ClassLoader.getSystemResource("").getPath();
    }
    
    public static List<File> listFiles(String path) {
        if (StringUtil.isNullOrEmpty(path)) {
            return null;
        }
        List<File> allFile = new ArrayList<File>();
        findFiles(path, allFile);
        return allFile;
    }
    
    public static List<File> listFiles(String path, String filter) {
        List<File> allFile = new ArrayList<File>();
        findFiles(path, allFile, filter);
        return allFile;
    }
    
    public static List<File> listFile(String path, String filter,
                                      boolean removeSuffix) {
        List<File> allFile = new ArrayList<File>();
        findFiles(path, allFile, filter);
        return allFile;
    }
    
    public static List<String> listFileName(String path, String filter,
                                            boolean removeSuffix) {
        List<File> allFile = new ArrayList<File>();
        findFiles(path, allFile, filter);
        if (!removeSuffix) {
            return allFile.stream().map(f -> f.getName()).collect(Collectors.toList());
        }
        List<String> resultFileNameList = new ArrayList<String>(allFile.size());
        for (File file : allFile) {
            String fileName = file.getName();
            int beginIndex = fileName.lastIndexOf(filter);
            if (beginIndex >= 0) {
                fileName = fileName.substring(0, beginIndex);
                resultFileNameList.add(fileName);
            }
        }
        return resultFileNameList;
    }
    
    private static void findFiles(String path, List<File> allFile) {
        findFiles(path, allFile, null);
    }
    
    private static void findFiles(String path, List<File> allFile,
                                  String filter) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                if (f.isFile()) {
                    if (filter == null) {
                        allFile.add(f);
                    } else {
                        if (f.getName().endsWith(filter)) {
                            allFile.add(f);
                        }
                    }
                } else if (f.isDirectory()) {
                    System.out.println(f.getPath());
                    findFiles(f.getPath(), allFile);
                }
            }
        } else if (file.isFile()) {
            allFile.add(file);
        }
    }
    
    public static URL findURLByFileName(String fileName) {
        ClassLoader cl = FileUtil.class.getClassLoader();
        return cl.getResource(fileName);
    }
    
    public static InputStream findInputStreamByFileName(String fileName) {
        try {
            URL url = findURLByFileName(fileName);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setUseCaches(false);
            return urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 获取字符串列表
     *
     * @param file
     * @param charsetName
     * @return
     * @throws FileNotFoundException
     */
    public static List<String> loadStringList(File file, String charsetName)
            throws FileNotFoundException {
        if (file == null || charsetName == null) {
            throw new NullPointerException();
        }
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (!file.isFile() || !file.canRead()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        return loadStringList(fileInputStream, charsetName);
    }
    
    /**
     * 获取字符串列表
     *
     * @param fileInputStream
     * @param charsetName
     * @return
     * @throws FileNotFoundException
     */
    public static List<String> loadStringList(FileInputStream fileInputStream,
                                              String charsetName) throws FileNotFoundException {
        if (fileInputStream == null || charsetName == null) {
            throw new NullPointerException();
        }
        BufferedReader reader = null;
        List<String> list = null;
        
        try {
            reader = new BufferedReader(new InputStreamReader(fileInputStream, charsetName));
            list = new ArrayList<String>();
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                list.add(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public static Properties getFileProperties(String path) throws IOException {
        Properties properties = new Properties();
        FileInputStream inStream = new FileInputStream(path);
        properties.load(inStream);
        return properties;
    }
    
}
