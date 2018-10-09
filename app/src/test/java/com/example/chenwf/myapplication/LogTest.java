package com.example.chenwf.myapplication;

import org.junit.Test;

/**
 * @author chenwf
 * @date 2018/10/9
 * @company Fotile智能厨电研究院
 */
public class LogTest {

    @Test
    public void TestWriteToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.i("我就是我哈哈哈");
        LogUtils.w("你是风儿");
        LogUtils.d("我是沙啊");
        LogUtils.e("我是error啊");
    }

    @Test
    public void TestWriteErrorToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.e("我是error啊" + System.currentTimeMillis());
        LogUtils.e("我是error啊" + System.currentTimeMillis());
        LogUtils.e("我是error啊" + System.currentTimeMillis());
    }

    @Test
    public void TestWriteFatalToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
    }

    @Test
    public void TestWriteToConsole() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.CONSOLE);
        LogUtils.i("你是风啊");
        LogUtils.w("你是风儿");
        LogUtils.d("我是沙啊");
        LogUtils.e("缠缠绵绵");
        LogUtils.f("到天涯");
    }

    @Test
    public void TestHadTAG() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.CONSOLE);
        String tag = "我是TAG";
        LogUtils.i(tag, "你是风啊");
        LogUtils.w(tag, "你是风儿");
        LogUtils.d(tag, "我是沙啊");
        LogUtils.e(tag, "缠缠绵绵");
        LogUtils.f(tag, "到天涯");
    }

}
