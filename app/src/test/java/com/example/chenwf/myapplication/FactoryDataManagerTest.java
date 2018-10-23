package com.example.chenwf.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chenwf
 * @date 2018/10/23
 * @company Fotile智能厨电研究院
 */
public class FactoryDataManagerTest {


    @Test
    public void TestUpVersionSuccess() {
        assertEquals(2, FactoryDataManager.getInstance().checkVersion("000200010000281B").getCurrentData().version);
    }

    @Test
    public void TestWrongData() {
        FactoryDataManager.getInstance().checkVersion("00020001000079DB");
        assertEquals(2, FactoryDataManager.getInstance().checkVersion("00030001000015DA").getCurrentData().version);
    }

    @Test
    public void TestLowVersion() {
        FactoryDataManager.getInstance().checkVersion("00020001000079DB");
        assertEquals(2, FactoryDataManager.getInstance().checkVersion("0001000100006C1B").getCurrentData().version);
    }

}
