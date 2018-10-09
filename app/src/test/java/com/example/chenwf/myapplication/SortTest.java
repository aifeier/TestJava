package com.example.chenwf.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author chenwf
 * @date 2018/9/11
 * @company Fotile智能厨电研究院
 * 排序算法测试
 */
public class SortTest {
    @Test
    public void TestBubbleSort() {
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6}, SortUtils.BubbleSort(new Object[]{1, 5, 6, 3, 4, 2}));
    }

    @Test
    public void TesInsertSort() {
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6}, SortUtils.InsertSort(new Object[]{1, 5, 6, 3, 4, 2}));
    }

    @Test
    public void TestFastSort() {
        Object[] o = new Object[]{1, 5, 6, 3, 4, 2};
        SortUtils.FastSort(o, 0, o.length - 1);
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6}, o);
    }

    public void TestBubbleSort1() {
        assertArrayEquals(new Object[]{"a", "b", "c", "d", "f", "g"}, SortUtils.BubbleSort(new Object[]{"a", "g", "d", "c", "f", "b"}));
    }

    @Test
    public void TesInsertSort1() {
        assertArrayEquals(new Object[]{"a", "b", "c", "d", "f", "g"}, SortUtils.InsertSort(new Object[]{"a", "g", "d", "c", "f", "b"}));
    }

    @Test
    public void TestFastSort1() {
        Object[] o = new Object[]{"a", "g", "d", "c", "f", "b"};
        SortUtils.FastSort(o, 0, o.length - 1);
        assertArrayEquals(new Object[]{"a", "b", "c", "d", "f", "g"}, o);
    }


}
