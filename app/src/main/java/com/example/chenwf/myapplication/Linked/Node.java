package com.example.chenwf.myapplication.Linked;

/**
 * @author chenwf
 * @date 2018/11/6
 * @company Fotile智能厨电研究院
 * 节点数据结构
 */
public class Node<E> {
    public E item;
    public Node<E> next;
    public Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
