package com.game.util.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.game.util.file.FileUtil.canRead;

public class FileContentUtil {
    
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
            list = new ArrayList<>();
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
     * 以二进制方式读取文件内容
     *
     * @param fileName
     * @return file content
     */
    public static byte[] readBytesFile(String fileName) throws Exception {
        if (!canRead(fileName)) {
            return null;
        }
        byte[] bytes = new byte[0];
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int len = bis.available();
        bytes = new byte[len];
        bis.read(bytes);
        fis.close();
        bis.close();
        return bytes;
    }
    
   
}
