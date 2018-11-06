/*
题目：（主要考察编码规范性）
实现双向链表数据结构，可以对外提供如下功能：
1. 支持四种插入方式：指定位置前，指定位置后，指定数据前，指定数据后
2. 支持数据查找、数据删除（允许删除单个数据或者一次性删除链表中相同的数据）
3. 链表数据元素的类型可以为整形、字符串、结构体、类等

要求：
1.	可以使用C/C++/JS/OC等语言实现
2.	不得使用各编程语言已有的函数库
3.	JS的代码在node.js环境下测试
4.	所有的接口函数必须提供完整的单元测试
*/

package com.example.chenwf.myapplication.Linked;

/**
 * @author chenwf
 * @date 2018/11/6
 * @company Fotile智能厨电研究院
 */
public class BidirectionalLinkedList<E> implements Collection<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public BidirectionalLinkedList() {

    }

    public Node<E> getObjectNode(Object o) {
        if (first == null && last == null) {
            return null;
        }
        if (o == null) {
            for (Node<E> x = first; x.next != first; x = x.next) {
                if (x.item == null)
                    return x;
            }
        } else {
            Node<E> x = first;
            do {
                if (o.equals(x.item))
                    return x;
                x = x.next;
            } while (x != first);
        }
        return null;
    }

    /*
     * 元素对应的位置，没有则返回-1
     * */
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x.next != first; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x.next != first; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /*
     * 获取指定位置元素
     * */
    public Node<E> getIndexNode(final int index) {
        if (first == null && last == null) {
            return null;
        }
        if (index < 0) {
            return null;
        } else {
            int count = 0;
            Node<E> x = first;
            do {
                if (count == index)
                    return x;
                x = x.next;
                count++;
            } while (x != first);
        }
        return null;
    }


    /**
     * 插入e数据到succ前面
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
//        modCount++;
    }

    /*
     * 从链表中删除节点
     * */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == last.next) {
            first = next;
        } else {
            prev.next = next;
            x.prev = prev;
        }

        if (next == first.prev) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = next;
        }

        x.item = null;
        size--;
//        modCount++;
        return element;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        //已经存在该元素
        if (getObjectNode(e) != null) {
            return false;
        }
        if (null == first && null == last) {
            //空链表
            Node<E> node = new Node<>(null, e, null);
            node.prev = node;
            node.next = node;
            first = node;
            last = node;
        } else {
            Node<E> node = new Node<>(last, e, first);
            last.next = node;
            first.prev = node;
            last = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean addToBefore(E e, E iE) {
        Node<E> node = getObjectNode(e);
        if (null == node) {
//            不存在该元素
            return false;
        }
        Node<E> newNode = new Node<E>(node.prev, iE, node);
        node.prev = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addToBefore(int index, E iE) {
        Node<E> node = getIndexNode(index);
        if (null == node) {
//            不存在该元素
            return false;
        }
        Node<E> insertNode = new Node<>(node.prev, iE, node);
        node.prev = insertNode;
        size++;
        return true;
    }

    @Override
    public boolean addToAfter(E e, E iE) {
        Node<E> node = getObjectNode(e);
        if (null == node) {
//            不存在该元素
            return false;
        }
        Node<E> newNode = new Node<E>(node, iE, node.next);
        node.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addToAfter(int index, E iE) {
        Node<E> node = getIndexNode(index);
        if (null == node) {
//            不存在该元素
            return false;
        }
        Node<E> newNode = new Node<E>(node, iE, node.next);
        node.next = newNode;
        size++;
        return true;
    }

    @Override
    public E getElementByIndex(int index) {
        Node<E> node = getIndexNode(index);
        return null != node ? node.item : null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            Node<E> x = first;
            do {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
                x = x.next;
            } while (x.next != first);
        } else {
            Node<E> x = first;
            do {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
                x = x.next;
            } while (x.next != first);
        }
        return false;
    }

    @Override
    public boolean removeAll(Object o) {
        int removeSize = 0;
        if (o == null) {
            Node<E> x = first;
            do {
                if (x.item == null) {
                    unlink(x);
                    removeSize++;
                }
                x = x.next;
            } while (x.next != first);
        } else {
            Node<E> x = first;
            do {
                if (o.equals(x.item)) {
                    unlink(x);
                    removeSize++;
                }
                x = x.next;
            } while (x.next != first);
        }
        if (removeSize <= 0)
            return false;
        return true;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
//        modCount++;
    }
}
