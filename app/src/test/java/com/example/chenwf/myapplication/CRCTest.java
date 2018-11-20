package com.example.chenwf.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chenwf
 * @date 2018/11/20
 * @company Fotile智能厨电研究院
 */
public class CRCTest {
    @Test
    public void success() {
        assertEquals("1010000011111110", Integer.toBinaryString(CRC.getCRCCode(0xFEFF)));
    }
}
