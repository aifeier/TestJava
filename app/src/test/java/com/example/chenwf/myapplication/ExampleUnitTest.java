package com.example.chenwf.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void success1() {
        assertEquals(true, Main.checkIsCallBack("level"));
    }

    @Test
    public void success2() {
        assertEquals(true, Main.contains("noon", "noon"));
    }

    @Test
    public void success3() {
        assertEquals(true, Main.checkIsCallBack("level"));
    }

    @Test
    public void fail1() {
        assertEquals(false, Main.contains("noo", "noon"));
    }

    @Test
    public void fail2() {
        assertEquals(false, Main.contains("success", "noon"));
    }

    @Test
    public void fail3() {
        assertEquals(false, Main.contains("aaaa", "noon"));
    }

    @Test
    public void fail4() {
        assertEquals(false, Main.contains("noo n", "noon"));
    }


}