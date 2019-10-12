package com.game.engine.common.util.file;


import com.ym.server.engine.common.util.collection.CollectionUtil;
import com.ym.server.engine.common.util.string.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author: caochaojie
 * @Date: 2019/3/18
 */
public class FileUtil {

    public static List<File> findFiles(String path, String filter) {
        List<File> allFile = new ArrayList<>();
        findFiles(path, allFile, null, filter);
        return allFile;
    }


    private static void findFiles(String path, List<File> allFile) {
        findFiles(path, allFile, null, null);
    }

    public static List<File> findFiles(String path, String fileNamePreFix, String filter) {
        List<File> allFile = new ArrayList<>();
        findFiles(path, allFile, fileNamePreFix, filter);
        return allFile;
    }

    private static void findFiles(String path, List<File> allFile,
                                  String fileNamePrefix, String fileFilter) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                if (f.isFile()) {
                    if (fileFilter == null) {
                        allFile.add(f);
                    } else {
                        String fileName = f.getName();
                        if (fileNamePrefix == null || fileNamePrefix.isEmpty()) {
                            if (fileName.endsWith(fileFilter)) {
                                allFile.add(f);
                            }
                        } else {
                            if (fileName.startsWith(fileNamePrefix) && fileName.endsWith(fileFilter)) {
                                allFile.add(f);
                            }
                        }
                    }
                } else if (f.isDirectory()) {
                    findFiles(f.getPath(), allFile);
                }
            }
        } else if (file.isFile()) {
            allFile.add(file);
        }
    }

    public static String getFileType(String filePath) throws Exception {
        int indexOf = filePath.lastIndexOf(".");
        if (indexOf == -1 || indexOf == 0) {
            throw new Exception("该文件没有文件类型");
        }
        return filePath.substring(indexOf);
    }

    public static String getFileName(String filePath) {
        int indexOf = filePath.lastIndexOf(".");
        return filePath.substring(0, indexOf);
    }

    public static void writeContent(File file, String content) {
        if (file == null || StringUtils.isBlank(content)) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static URL findURLByFileName(String fileName) {
        ClassLoader cl = FileUtil.class.getClassLoader();
        return cl.getResource(fileName);
    }

    public static void writeLines(Collection<String> list, File file, Charset charset, boolean isAppend) {
        if (CollectionUtil.isEmpty(list) || file == null || charset == null) {
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, isAppend));
            Throwable throwable = null;
            try {
                for (String content : list) {
                    writer.write(content);
                    writer.newLine();
                }
                writer.flush();
            } catch (Throwable v1) {
                throwable = v1;
                throw v1;
            } finally {
                if (throwable != null) {
                    try {
                        writer.close();
                    } catch (Throwable v2) {
                        throwable.addSuppressed(v2);
                    }
                } else {
                    writer.close();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}

