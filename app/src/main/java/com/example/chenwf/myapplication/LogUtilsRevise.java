package com.example.chenwf.myapplication;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenwf
 * @date 2018/10/9
 * @company Fotile智能厨电研究院
 */

/*
实现日志管理系统，具有如下功能：
        1. 消息日志必须包含时间戳和日志，时间戳的格式"年"-“月”-“日” “时”：“分”：“秒”."纳秒"，比如“2018-10-09 14:02:10.123456789”
        2. 允许用户选择日志消息的输出模式：控制台（console）或者文件
        3. 能对日志进行分级显示：INFO，DEBUG，WARNING，ERROR，FATAL
        4. ERROR，FATAL等级的日志信息需要分别保存在err.log和fatal.log文件

        要求：
        1. 可以使用C/C++/JS/OC等语言实现
        2. 不得使用各编程语言已有的函数库
        3. JS的代码在node.js环境下测试
        4. 所有的接口函数必须提供完整的单元测试
*/

public class LogUtilsRevise {
    private LogType logType = LogType.CONSOLE;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private File outFile;
    private File errorFile, fatalFile;

    private LogUtilsRevise() {
        setOutPutFolderPath(null);
    }

    private static class LogUtilsHolder {
        private final static LogUtilsRevise instance = new LogUtilsRevise();
    }

    public static LogUtilsRevise getInstance() {
        return LogUtilsHolder.instance;
    }

    public LogUtilsRevise setLogType(LogType logType) {
        this.logType = logType;
        return this;
    }

	// 修订要求：这种实现方式时，如果用户不调用这个接口，则err.log和fatal.log不会生成
    public LogUtilsRevise setOutPutFolderPath(String folderPath) {
        if (null == folderPath) {
            folderPath = "d://";
        }
        if (!new File(folderPath).exists()) {
            new File(folderPath).mkdirs();
        }
        outFile = new File(folderPath, new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS").format(new Date()) + ".log");
        errorFile = new File(folderPath, "err.log");
        fatalFile = new File(folderPath, "fatal.log");
        return this;
    }

    public static void i(String msg) {
        i("", msg);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        getInstance().log(LogLevel.INFO, tag, msg, tr);
    }

    public static void d(String msg) {
        d("", msg);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable tr) {
        getInstance().log(LogLevel.DEBUG, tag, msg, tr);
    }

    public static void w(String msg) {
        w("", msg);
    }

    public static void w(String tag, String msg) {
        w("", msg, null);
    }

    public static void w(String tag, String msg, Throwable tr) {
        getInstance().log(LogLevel.WARNING, tag, msg, tr);
    }

    public static void e(String msg) {
        e("", msg);
    }

    public static void e(String tag, String msg) {
        e("", msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        getInstance().log(LogLevel.ERROR, tag, msg, tr);
    }

    public static void f(String msg) {
        f("", msg);
    }

    public static void f(String tag, String msg) {
        f("", msg, null);
    }

    public static void f(String tag, String msg, Throwable tr) {
        getInstance().log(LogLevel.FATAL, tag, msg, tr);
    }

	// 修订要求：实现不符合要求，题目要求fatal，error级别的日志
	// 除了写到err.log或者fatal.log以外，还需要输出到console或者日志文件
    private void log(LogLevel logLevel, String tag, String msg, Throwable tr) {
        try {
            String log = getCurrentTimeStr() + " " + logLevel.toString() + " " + tag + " : " + msg + "\r\n";
            switch (logType) {
                case FILE:
                    switch (logLevel) {
                        case ERROR:
                            writeToFile(errorFile, log, tr);
                            break;
                        case FATAL:
                            writeToFile(fatalFile, log, tr);
                            break;
                        default:
                            writeToFile(outFile, log, tr);
                    }
                    break;
                case CONSOLE:
                    System.out.print(log);
                    break;
            }
        }catch (Throwable e){
            e.printStackTrace();
            if(null != tr){
                tr. printStackTrace();
            }
        }
    }

    private void writeToFile(File file, String log, Throwable tr) {
        if (null == file) {
            return;
        }
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;

        try {
            outSTr = new FileOutputStream(file, true);
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            Buff.write(log.getBytes("utf-8"));
            Buff.flush();
            Buff.close();
            long end0 = System.currentTimeMillis();

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

    public String getCurrentTimeStr() throws Throwable{
        String nanoTime = String.valueOf(System.nanoTime());
        return formatter.format(new Date()) + nanoTime.substring(nanoTime.length() - 6, nanoTime.length());
    }

    public enum LogType {
        CONSOLE,
        FILE
    }

    public enum LogLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR,
        FATAL
    }
}
