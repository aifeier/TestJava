package com.example.chenwf.myapplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenwf
 * @date 2018/9/11
 * @company Fotile智能厨电研究院
 */
public class SortUtils {


//    public static abstract class MyNum {
//        public Object o;
//
//        public void MyNum(Object o) {
//            this.o = o;
//        }
//
//        /*通用比较方法*/
//        public abstract boolean compareTo(Object o);
//    }

    /*比较不同类型的数据，o > n 时返回true*/
    public static boolean CheckMax(Object o, Object n) {
        boolean isMax = (o instanceof Integer && (Integer) o >= (Integer) n)
                || (o instanceof Long && (Long) o >= (Long) n)
                || (o instanceof Double && (Double) o >= (Double) n)
                || (o instanceof String && ((String) o).compareTo((String) n) >= 0);
        if (!(o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof String)) {
            try {
                Method method = o.getClass().getMethod("compareTo", o.getClass());
                isMax = ((int) method.invoke(o, n)) >= 0;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return isMax;
    }

    /*
     * 冒泡排序
     */
    public static Object[] BubbleSort(Object[] arr) {
        if (null != arr) {
            for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
                for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                    String s = "";
                    if (CheckMax(arr[j], arr[j + 1])) {
                        Object temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
        return arr;
    }

    /*插入算法*/
    public static Object[] InsertSort(Object[] source) {
        int i, j;
        Object insertNode;// 要插入的数据
        // 从数组的第二个元素开始循环将数组中的元素插入
        for (i = 1; i < source.length; i++) {
            // 设置数组中的第2个元素为第一次循环要插入的数据
            insertNode = source[i];
            j = i - 1;
            // 如果要插入的元素小于第j个元素，就将第j个元素向后移
            while ((j >= 0) && CheckMax(source[j], insertNode)) {
                source[j + 1] = source[j];
                j--;
            }
            // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
            source[j + 1] = insertNode;
        }
        return source;
    }


    /*快速排序*/
    public static void FastSort(Object[] a, int low, int high) {
        int start = low;
        int end = high;
        Object key = a[low];

        while (end > start) {
            //从后往前比较
            while (end > start && CheckMax(a[end], key))  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (CheckMax(key, a[end])) {
                Object temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && CheckMax(key, a[start]))//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (CheckMax(a[start], key)) {
                Object temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) FastSort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) FastSort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }
}
