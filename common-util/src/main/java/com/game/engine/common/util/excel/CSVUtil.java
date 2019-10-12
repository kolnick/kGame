package com.game.engine.common.util.excel;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caochaojie
 * @date 2019/09/21
 */
public class CSVUtil {
    public static CSVData readConfigDataFromUrl(String str, int skipLine) {
        BufferedReader reader = null;
        List<String> lines;
        try {
            reader = new BufferedReader(new InputStreamReader((new URL(str)).openStream()));
            String tempString = null;
            lines = new ArrayList<>();
            while ((tempString = reader.readLine()) != null) {
                lines.add(tempString);
            }
            reader.close();
            return new CSVData(lines, skipLine);
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ignored) {
            }

        }
        return null;

    }

    public static CSVData read(String filepath, int skipLine) {
        BufferedReader br = null;

        List<String> lines;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
            String line = null;
            lines = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return new CSVData(lines, skipLine);
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }
        return null;
    }

}
