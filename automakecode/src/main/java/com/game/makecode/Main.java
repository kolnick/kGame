package com.game.makecode;

import com.game.config.AbstractConfigData;
import com.game.makecode.bean.JavaBean;
import com.game.makecode.constant.GlobalConstant;
import com.game.makecode.constant.JavaDataType;
import com.game.makecode.util.ExcelUtil;
import com.game.util.check.CheckerUtil;
import com.game.util.convert.ConvertUtil;
import com.game.util.file.FileUtil;
import com.game.util.string.StringUtil;
import com.sun.codemodel.*;
import com.sun.codemodel.writer.SingleStreamCodeWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static String path = "release/data";
    private static String outPath = "autocode/";
    private static String packagePath = "com.game.config.model";

    public static void main(String[] args) {
        List<File> files = FileUtil.listFiles(path, ".xls");
        if (files != null) {
            for (File file : files) {
                Workbook workBook = ExcelUtil.getWorkBook(file);
                String fileName = file.getName();
                if (workBook == null) {
                    System.out.println("文件" + fileName + "加载错误");
                } else {
                    int numberOfSheets = workBook.getNumberOfSheets();

                    for (int i = 0; i < numberOfSheets; i++) {
                        Sheet sheet = workBook.getSheetAt(i);
                        // 有多少行
                        int lastRowNum = sheet.getLastRowNum();
                        if (lastRowNum < GlobalConstant.SKIP_ROW) {
                            logger.info("文件:" + fileName + "格式有问题," + "缺少必要的" + GlobalConstant.SKIP_ROW + "行数据");
                            continue;
                        }
                        String sheetName = sheet.getSheetName();
                        String[] sheetNameStrAtr = ConvertUtil.getStringArr(sheetName, "_");
                        // 检测名字是否符合标准
                        if (!isValidSheetName(sheetNameStrAtr)) {
                            logger.info("文件:" + fileName + "格式有问题," + "sheet表名格式不正确 ");
                            continue;
                        }
                        JavaBean javaBean = parseToJavaBeanStrut(sheet);
                        javaBean.setFileName(sheetNameStrAtr[1]);
                        makeJavaFile(javaBean);
                    }
                }
            }
        }
    }

    private static boolean isValidSheetName(String[] stringArr) {
        if (stringArr == null || stringArr.length == 0) {
            return false;
        }
        if (stringArr.length != 2) {
            return false;
        }
        String s = stringArr[0];
        if (s.startsWith("c_") || s.equals("s_") || s.equals("cs_")) {
            String s1 = stringArr[1];
            // 只能使用
            return CheckerUtil.isLetter(s1);
        }
        return false;
    }

    private static void makeJavaFile(JavaBean javaBean) {
        if (javaBean != null) {
            String fileName = javaBean.getFileName();
            List<String> codeSpace = javaBean.getClientOrServer();
            List<String> property = javaBean.getProperty();
            List<String> comment = javaBean.getComment();
            List<String> dataType = javaBean.getDataType();
            int codeSpaceSize = codeSpace.size();
            int propertySize = property.size();
            int commentSize = comment.size();
            int dataTypeSize = dataType.size();

            JCodeModel codeModel = new JCodeModel();
            JPackage jPackage = codeModel._package(packagePath);
            try {
                fileName = StringUtil.toFirstSuperCase(fileName);
                fileName += "Config";
                JDefinedClass jDefinedClass1 = jPackage._class(fileName);
                if (codeSpaceSize > 0 && propertySize > 0 && commentSize > 0 && dataTypeSize > 0) {
                    if (codeSpaceSize != propertySize || codeSpaceSize != commentSize || codeSpaceSize != dataTypeSize) {

                    } else {
                        for (int i = 0; i < codeSpaceSize; i++) {
                            String cs = codeSpace.get(i);
                            String bp = property.get(i);
                            String ct = comment.get(i);
                            String dt = dataType.get(i);
                            if (cs.equals(GlobalConstant.CodeSpaceType.SERVER) || cs.equals(GlobalConstant.CodeSpaceType.CS)) {
                                if (JavaDataType.containsDataType(dt)) {
                                    Class dataType1 = JavaDataType.getDataType(dt);
                                    JFieldVar field = jDefinedClass1.field(JMod.PRIVATE, dataType1, bp);
                                    JDocComment javadoc = field.javadoc();
                                    javadoc.addXdoclet(ct);
                                    // 生成 get set 方法
                                    generateGetMethod(jDefinedClass1, dataType1, bp);
                                    generateSetMethod(jDefinedClass1, dataType1, bp);
                                }
                            }
                        }
                        jDefinedClass1._extends(AbstractConfigData.class);
                        codeModel.build(new File(outPath));
                        codeModel.build(new SingleStreamCodeWriter(System.out));
                    }
                }
            } catch (JClassAlreadyExistsException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateGetMethod(JDefinedClass jClass, Class<?> type,
                                          String name) {
        String s = StringUtil.toFirstSuperCase(name);
        JMethod method = jClass.method(JMod.PUBLIC, type, "get" + s);
        JBlock body = method.body();
        body.directStatement("return" + "\t" + name + ";");
    }

    private static void generateSetMethod(JDefinedClass jClass, Class<?> type,
                                          String name) {
        String s = StringUtil.toFirstSuperCase(name);
        JMethod method = jClass.method(JMod.PUBLIC, void.class, "set" + s);
        method.param(type, name);
        JBlock body = method.body();
        body.directStatement("this." + name + "=" + name + ";");
    }

    private static JavaBean parseToJavaBeanStrut(Sheet sheet) {
        JavaBean javaBean = new JavaBean();
        for (int j = 0; j < GlobalConstant.SKIP_ROW; j++) {
            Row row = sheet.getRow(j);
            // 获取这一行有多少列
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            short firstCellNum = row.getFirstCellNum();

            // 先加载前6行数据
            for (int c = firstCellNum; c < lastCellNum; c++) {
                // 获取这一行的第几列的数据
                Cell cell = row.getCell(c);
                if (cell == null) {
                    continue;
                }
                String cellValue = cell.getStringCellValue();
                switch (j) {
                    case GlobalConstant.RowType.COMMENT: {
                        javaBean.getComment().add(cellValue);
                        break;
                    }
                    case GlobalConstant.RowType.DATA_TYPE: {
                        javaBean.getDataType().add(cellValue);
                        break;
                    }
                    case GlobalConstant.RowType.FIELD: {
                        javaBean.getProperty().add(cellValue);
                        break;
                    }
                    case GlobalConstant.RowType.BLANK: {
                        javaBean.getBlank().add(cellValue);
                        break;
                    }
                    case GlobalConstant.RowType.SPACE: {
                        javaBean.getClientOrServer().add(cellValue);
                    }
                }
            }
        }
        return javaBean;
    }

}
