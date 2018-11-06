package com.example.chenwf.myapplication;

import com.example.chenwf.myapplication.Linked.BidirectionalLinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chenwf
 * @date 2018/11/6
 * @company Fotile智能厨电研究院
 */
public class BidirectionalLinkedListTest {
    @Test
    public void TestAdd() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("abc");
        assertEquals(1, linkedList.size());
    }

    @Test
    public void TestAddAfter() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("a");
        linkedList.addToAfter("a","b");
        assertEquals(2, linkedList.size());
    }

    @Test
    public void TestAddBefore() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("a");
        linkedList.addToBefore("a","z");
        assertEquals(2, linkedList.size());
    }

    @Test
    public void TestAddBeforeByIndex() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("a");
        linkedList.addToBefore(0,"z");
        assertEquals(2, linkedList.size());
    }

    @Test
    public void TestAddAfterByIndex() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("a");
        linkedList.addToBefore(0,"b");
        assertEquals(2, linkedList.size());
    }

    @Test
    public void TestRemove() {
        BidirectionalLinkedList<String> linkedList = new BidirectionalLinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.remove("b");
        assertEquals(2, linkedList.size());
    }
}
