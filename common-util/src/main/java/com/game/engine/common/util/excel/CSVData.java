package com.game.engine.common.util.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caochaojie
 * @date 2019/09/21
 */
public class CSVData {

    public String[] tableHead;
    public List<Map<String, String>> tableRows;
    private int skipLine;

    public CSVData(List<String> lines, int skipLine) {
        this.skipLine = skipLine;
        this.skip(lines);
        this.readTH(lines);
        this.readTR(lines);
    }

    public void skip(List<String> lines) {
        for (int i = 0; i < this.skipLine && !lines.isEmpty(); ++i) {
            lines.remove(0);
        }

    }

    public void readTH(List<String> lines) {
        String line;
        if (this.skipLine >= 0) {
            line = lines.remove(0);
            this.tableHead = line.trim().split(",");
        } else {
            line = lines.get(0);
            String[] t = line.trim().split(",");
            this.tableHead = new String[t.length];

            for (int i = 0; i < t.length; ++i) {
                this.tableHead[i] = String.valueOf((char) (65 + i));
            }
        }

    }

    public void readTR(List<String> lines) {
        this.tableRows = new ArrayList<>();
        String line = null;
        String[] lineArray = null;

        for (int i = 0; i < lines.size(); ++i) {
            Map<String, String> tr = new HashMap<>();
            line = lines.get(i);
            lineArray = line.split(",", -1);

            for (int j = 0; j < lineArray.length; ++j) {
                if (j < this.tableHead.length) {
                    String col = lineArray[j];
                    tr.put(this.tableHead[j], col);
                }
            }
            this.tableRows.add(tr);
        }

    }

    @Override
    public String toString() {
        return "ListConfigData:" + this.tableHead.toString() + "=>" + this.tableRows.toString();
    }

}
