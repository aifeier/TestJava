/*
* 题目：
任何一个成熟的产品都包含有工厂模式，它包含很多工厂调试数据，进入该模式时，用户可以调整设备的环境变量和相关参数，
如C2.i设备根据云平台环境变量决定接入到”测试“云平台还是”线上“云平台，现在需要开发一个工厂模式的工厂数据管理模块；
可以完成如下功能：
1. 工程数据包含如下字段：
    - 版本号（4字节整型：从数字1开始依次递增）
	- 物联网云平台环境类型（4字节整型：“线上为1”，“测试为0”，默认值为“0”）
	- 菜谱云环境类型（4字节整型：“线上为1”，“测试为0”，默认值为“0”）
	- CRC16校验（校验整个工厂数据含版本号）
2. 当系统中保存的工厂数据文件中的版本小于当前版本，则用改版的模块参数重置工厂数据文件
3. 当系统中保存的工厂数据文件损坏（如CRC16校验失败），则用改版的模块参数重置工厂数据文件

要求：
1.	可以使用C/C++/JS/OC等语言实现
2.	不得使用各编程语言已有的函数库
3.	JS的代码在node.js环境下测试
4.	所有的接口函数必须提供完整的单元测试
5.  CRC16校验算法可以在网上移植现成的
* */

package com.example.chenwf.myapplication;

import com.example.chenwf.myapplication.Utils.CRC16M;
import com.example.chenwf.myapplication.Utils.FileUtils;

import java.io.File;

/**
 * @author chenwf
 * @date 2018/10/23
 * @company Fotile智能厨电研究院
 */
public class FactoryDataManager {

    public class EngineeringData {
        private File file = new File("d://factory.txt");
        private String saveData = null;
        public int version;
        public int cloudEnvironment;
        public int menuEnvironment;


        public EngineeringData(String data) {
            if (null == data || data.length() < 12)
                return;
            String fileData = FileUtils.readFileByLines(file);
            boolean currentDataRight = "" != fileData && CRC16M.getBufHexStr(CRC16M.getSendBuf(fileData.substring(0, 12))).equals(fileData);
            //第一次初始化
            if (null == saveData && currentDataRight) {
                saveData = fileData;
                initData(fileData);
            }
            //校验CRC
            if (CRC16M.getBufHexStr(CRC16M.getSendBuf(data.substring(0, 12))).equals(data)) {
                int newVersion = Integer.valueOf(data.substring(0, 4));
                if (!currentDataRight || newVersion > version) {
                    //校验本地数据是否正确
                    saveData = data;
                    FileUtils.writeToFile(file, saveData);
                    initData(data);
                }
            }

        }

        private void initData(String data) {
            version = Integer.valueOf(data.substring(0, 4));
            cloudEnvironment = Integer.valueOf(data.substring(4, 8));
            menuEnvironment = Integer.valueOf(data.substring(8, 12));
        }


    }

    private EngineeringData engineeringData;

    private static class FactoryDataManagerHolder {
        private final static FactoryDataManager instance = new FactoryDataManager();
    }

    public static FactoryDataManager getInstance() {
        return FactoryDataManagerHolder.instance;
    }

    public FactoryDataManager checkVersion(String data) {
        this.engineeringData = new EngineeringData(data);
        return this;
    }

    public EngineeringData getCurrentData() {
        return engineeringData;
    }


}
