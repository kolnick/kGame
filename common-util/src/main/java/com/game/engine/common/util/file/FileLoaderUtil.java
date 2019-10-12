package com.game.engine.common.util.file;

import com.ym.server.engine.common.util.string.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;

public class FileLoaderUtil {

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
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException();
        }
        if (!file.canRead()) {
            return Collections.emptyList();
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

    public static String loadFileToString(String path) {
        List<String> list = null;
        try {
            FileInputStream in = new FileInputStream(path);
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(inReader);
            try {
                list = new ArrayList<>();
                String buf = null;
                while ((buf = bufReader.readLine()) != null) {
                    list.add(buf);
                }
                bufReader.close();
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
            return "";
        }
        return StringUtils.listToString(list);
    }

    public static void writeLines(Collection<String> list, File file, Charset charset, boolean isAppend) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, isAppend));
            Throwable var5 = null;

            try {
                if (list != null) {
                    for (String content : list) {
                        writer.write(content);
                        writer.newLine();
                    }
                    writer.flush();
                }
            } catch (Throwable var17) {
                var5 = var17;
                throw var17;
            } finally {
                if (var5 != null) {
                    try {
                        writer.close();
                    } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                    }
                } else {
                    writer.close();
                }
            }

        } catch (IOException var19) {
            var19.printStackTrace();
        }

    }

    public static URL findURLByFileName(String fileName) {
        ClassLoader cl = FileLoaderUtil.class.getClassLoader();
        return cl.getResource(fileName);
    }

    public static InputStream findInputStreamByFileName(String fileName) {
        try {
            URL url = findURLByFileName(fileName);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setUseCaches(false);
            return urlConnection.getInputStream();
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
