package com.example.chenwf.myapplication;

import java.math.BigInteger;

/**
 * @author chenwf
 * @date 2018/11/20
 * @company Fotile智能厨电研究院
 * <p>
 * 题目：（主要考察编码规范性）
 * CRC是数据通信领域中最常用的一种查错校验码，其特征是信息字段和校验字段的长度可以任意选定;
 * 现需要实现CRC-16的算法，算法如下：
 * 1. 预置1个16位的寄存器为十六进制FFFF（即全为1），称此寄存器为CRC寄存器；
 * 2. 把第一个8位二进制数据（既通讯信息帧的第一个字节）与16位的CRC寄存器的低8位相异或，把结果放于CRC寄存器，高八位数据不变；
 * 3. 把CRC寄存器的内容右移一位（朝低位）用0填补最高位，并检查右移后的移出位；
 * 4. 如果移出位为0：重复第3步（再次右移一位）；如果移出位为1，CRC寄存器与多项式A001（1010 0000 0000 0001）进行异或；
 * 5. 重复步骤3和4，直到右移8次，这样整个8位数据全部进行了处理；
 * 6. 重复步骤2到步骤5，进行通讯信息帧下一个字节的处理；
 * 7. 将该通讯信息帧所有字节按上述步骤计算完成后，得到的16位CRC寄存器的高、低字节进行交换；
 * 8. 最后得到的CRC寄存器内容即为：CRC码。
 * <p>
 * 要求：
 * 1.	可以使用C/C++/JS/OC等语言实现
 * 2.	不得使用各编程语言已有的函数库
 * 3.	JS的代码在node.js环境下测试
 * 4.	所有的接口函数必须提供完整的单元测试
 */
public class CRC {
    public static int DEFAULT_VALUE = 0b1111111111111111;
    public static int A001 = 0b1010000000000001;
    public static int A000 = 0b0111111111111111;

    /*
     * @param data:8位16进制信息
     * */
    public static int getCRCCode(int data) {
        int i = 0;
        int crc = DEFAULT_VALUE;
        /*补齐为8的倍数*/
        String binaryStr = Integer.toBinaryString(data);
        int length = binaryStr.length();
        int remainder = length % 8;
        for (i = 0; i < remainder; i++) {
            binaryStr += "0";
            length++;
        }
        data = Integer.parseInt(binaryStr.trim(), 2);

        int size = length / 8;
        for (int numSize = 1; numSize <= size; ) {
            //拿到高8位
            int temp = data;
            int high = ((temp << (i * 8)) >> length) & 0xFF;


            crc = (crc & 0xFF00) | (crc & 0x00FF) ^ high;

            //右移8次
            for (i = 1; i <= 8; ) {
                int highOut = 0;
                do {
                    //移除的最高位
                    highOut = crc >> (16 - i);

                    String t = "";
                    for (int j = 0; j < i; j++) {
                        t += "1";
                    }
                    temp = ~(Integer.parseInt(t.trim(), 2) << (16 - i));
                    //移除后的数据
                    crc = temp & crc;
                    //右移一次
                    i++;
                } while (highOut != 0);
                crc = crc ^ A001;
            }

            numSize++;

        }
        //高低字节进行交换
        crc = (crc & 0xFF00 >> 8) | (((crc << 8) >> 16) << 8);
        System.out.println(Integer.toBinaryString(crc));
        return crc;
    }

}
