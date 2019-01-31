package com.game.config.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.game.config.bean.TableData;

public class XlsUtil
{
	public static Workbook getWorkBook(File file)
	{
		Workbook workbook = null;
		try
		{
			File f = new File("");
			System.out.println(f.getAbsolutePath());
			System.out.println(file.exists());
			FileInputStream fileStream = new FileInputStream(file);
			String filePath = file.getPath();
			String fileType = filePath.substring(filePath.lastIndexOf("."), filePath.length());
			if (fileType.equals(".xls"))
			{
				workbook = new HSSFWorkbook(fileStream);// 创建 Excel 2003 工作簿对象
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return workbook;
	}

	public static TableData load(File file, short skipLine)
	{
		Workbook workBook = getWorkBook(file);
		if (workBook == null)
			return null;
		return getTableDataList(workBook, skipLine);
	}

	/**
	 * @param workBook
	 * @param skipLine
	 *            跳过几行
	 * @return
	 */
	public static TableData getTableDataList(Workbook workBook, short skipLine)
	{
		int numberOfSheets1 = workBook.getNumberOfSheets();
		if (numberOfSheets1 < 1)
			return null;
		Sheet sheet = workBook.getSheetAt(0);
		// 有多少行
		int lastRowNum = sheet.getLastRowNum();
		List<List<String>> rowLines = new ArrayList<>();
		for (int j = 0; j <= lastRowNum; j++)
		{
			Row row = sheet.getRow(j);
			List<String> lines = new ArrayList<>();
			if (row != null)
			{
				short lastCellNum = row.getLastCellNum();
				for (int k = 0; k < lastCellNum; k++)
				{
					Cell cell = row.getCell(k);
					if (cell != null)
					{
						String content = cell.getStringCellValue();
						lines.add(content);
					}
					else
					{
						lines.add(null);
					}
				}
				rowLines.add(lines);
			}
		}
		TableData tableData = new TableData(workBook.getSheetName(0), rowLines, skipLine);
		return tableData;
	}
}
