package com.game.makecode.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    public static Workbook getWorkBook(File file) {
        Workbook workbook = null;
        try {
            FileInputStream fileStream = new FileInputStream(file);
            String filePath = file.getPath();
            String fileType = filePath.substring(filePath.lastIndexOf("."), filePath.length());
            if (fileType.equals(".xls")) {
                workbook = new HSSFWorkbook(fileStream);// 创建 Excel 2003 工作簿对象
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

}
