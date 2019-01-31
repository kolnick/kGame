package com.game.config.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.config.constant.ConfigConstant;
import com.game.util.convert.ConvertUtil;
import com.game.util.log.Log;
import com.game.util.string.StringUtil;

public class TableData {
    private static final Logger log = LoggerFactory.getLogger(TableData.class);

    private List<TableTitleRule> tableTitleRule;

    public List<Map<String, String>> tableRows;

    private int skipLine;

    private String tableName;

    public TableData() {

    }

    public TableData(String tableName, List<List<String>> lines, int skipLine) {
        this.tableName = tableName;
        this.skipLine = skipLine;
        // 跳过其他注释行
        skip(lines);
        // 读表头
        readTH(lines);
        // 读表行
        readTR(lines);
        // 清理内存
        clear();
    }

    private void clear() {

    }

	private void readTR(List<List<String>> lines)
	{
		tableRows = new ArrayList<>();
		int lineSize = lines.size();
		for (int i = 2; i < lineSize; i++)
		{
			List<String> columnList = lines.get(i);
			if (columnList != null)
			{
				int size = columnList.size();
				Map<String, String> columnMap = new HashMap<>();
				for (int k = 0; k < size; k++)
				{
					String column = columnList.get(k);
					TableTitleRule cellRule = tableTitleRule.get(k);
					if (cellRule != null)
					{
						String title = cellRule.getTitle();
						if (!cellRule.isEmpty())
						{
							if (StringUtil.isNullOrEmpty(column))
							{
								Log.logInfo(log, String.format("表格[%s] 中 字段 [%s] 第[%d] 行 不允许 数据为空", tableName, title, k + 1));
							}
						}
						if (!StringUtil.isNullOrEmpty(title))
							columnMap.put(title, column);
					}

				}
				tableRows.add(columnMap);
			}
		}
	}

    /**
     * 读表头
     *
     * @param lines
     */
    private void readTH(List<List<String>> lines) {
        if (lines != null && lines.size() > 0) {
            List<String> list = lines.get(0);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TableTitleRule tableTitleRule = this.tableTitleRule.get(i);
                if (tableTitleRule != null) {
                    tableTitleRule.setTitle(list.get(i));
                }
            }
        }
    }

    private void skip(List<List<String>> lines) {
        tableTitleRule = new ArrayList<>();

        for (int i = 0; i < skipLine; i++) {
            if (lines.isEmpty()) {
                break;
            }
            if (i != ConfigConstant.TableRule.CHECK_EMPTY) {
                continue;
            }
            List<String> columnList = lines.get(i);
            if (columnList == null || columnList.isEmpty()) {
                continue;
            }
            for (String column : columnList) {
                TableTitleRule cellRule = new TableTitleRule();
                if (!StringUtil.isNullOrEmpty(column)) {
                    cellRule.setEmpty(ConvertUtil.getBoolean(column));
                    tableTitleRule.add(cellRule);
                }
            }
        }

        for (int i = 0; i < skipLine; i++) {
            if (lines.isEmpty()) {
                break;
            }
            lines.remove(0);
        }

    }

    public List<TableTitleRule> getTableTitleRule() {
        return tableTitleRule;
    }

    public void setTableTitleRule(List<TableTitleRule> tableTitleRule) {
        this.tableTitleRule = tableTitleRule;
    }

    public List<Map<String, String>> getTableRows() {
        return tableRows;
    }

    public void setTableRows(List<Map<String, String>> tableRows) {
        this.tableRows = tableRows;
    }

    public int getSkipLine() {
        return skipLine;
    }

    public void setSkipLine(int skipLine) {
        this.skipLine = skipLine;
    }
}
