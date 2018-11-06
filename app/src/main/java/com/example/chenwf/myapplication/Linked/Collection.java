package com.example.chenwf.myapplication.Linked;

/**
 * @author chenwf
 * @date 2018/11/6
 * @company Fotile智能厨电研究院
 */
public interface Collection<E> {
    /*
    * 返回当前数据长度
    * */
    int size();

    /*
    * 是否为空
    * */
    boolean isEmpty();

    /*
    * 是否包含某个数据
    *
    * */
    boolean contains(Object o);


    /*
     * 插入数据到最后一个节点
     * */
    boolean add(E e);

    /*
    * 插入到指定数据前
    * @param e: 指定数据
    * @param iE: 需要被插入的数据
    * */
    boolean addToBefore(E e, E iE);

    /*
     * 插入到指定数据前
     * @param index: 指定位置
     * @param iE: 需要被插入的数据
     * */
    boolean addToBefore(int index, E iE);

    /*
     * 插入到指定数据后
     * @param e: 指定数据
     * @param iE: 需要被插入的数据
     * */
    boolean addToAfter(E e, E iE);

    /*
     * 插入到指定位置后
     * @param index: 指定位置
     * @param iE: 需要被插入的数据
     * */
    boolean addToAfter(int index, E iE);

    /*
    * 查找指定位置元素
    * */
    E getElementByIndex(int index);


    /*
    * 移除第一个匹配的指定元素
    * */
    boolean remove(Object o);

    /*
     * 移除匹配指定元素的所有元素
     * */
    boolean removeAll(Object o);

    /*
    * 清空数据
    * */
    void clear();
}
