package com.example.chenwf.myapplication.Utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author chenwf
 * @date 2018/10/23
 * @company Fotile智能厨电研究院
 */
public class FileUtils {
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            StringBuffer stringBuffer = new StringBuffer();
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                stringBuffer.append(tempString);
                line++;
            }
            reader.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return "";
    }

    //写文件
    public static void writeToFile(File file, String data) {
        if (null == file) {
            return;
        }
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;

        try {
            outSTr = new FileOutputStream(file);
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            Buff.write(data.getBytes("utf-8"));
            Buff.flush();
            Buff.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
