package com.wyl.ds.singlylinkedlist;

import java.util.Comparator;

/**
 * Created by wangyulin on 18/03/2018.
 */
public class SinglyLinkedListDemo {

    public static void main(String[] args) {
        SinglyLinkedList<String> linkedList = new SinglyLinkedList<>();
        linkedList.addNode("Hello");
        linkedList.addNode("World");
        //linkedList.addNode("World");
        linkedList.addNode("Node");
        linkedList.addNode("BeiJing");
        linkedList.insertPosNode(1, new Node("OK"));
        linkedList.insertPosData(2, "China");
        //System.out.println("------");
        //linkedList.traverse();

        /*System.out.println("------");
        Node target = linkedList.findNode("Hello");*/

        //测试合并链表
        SinglyLinkedList<String> otherLinkedList = new SinglyLinkedList<>();
        otherLinkedList.addNode("What");
        otherLinkedList.addNode("Where");

        linkedList.mergeLinkedList(otherLinkedList);

        //System.out.println("------");
        //linkedList.traverse();

        /*SinglyLinkedList newLinkedList = SinglyLinkedList.revertBySinglyLinkedList(linkedList);
        System.out.println("------");
        newLinkedList.traverse();*/

        /*System.out.println("------");
        SinglyLinkedList newSinglyLinkedList = SinglyLinkedList.revertBySinglyLinkedListV2(linkedList);
        newSinglyLinkedList.traverse();*/

        //获取链表长度
        /*System.out.println("------");
        System.out.println(SinglyLinkedList.getLength(linkedList));*/

        //测试获取指定位置的节点信息
        /*System.out.println("------");
        SinglyLinkedList singlyLinkedList_2 = new SinglyLinkedList();
        System.out.println(linkedList.getElement(4).data);*/

        //测试删除链表尾节点
        /*System.out.println("------");
        linkedList.deleteTailNode();
        linkedList.traverse();*/

        //测试删除链头尾节点
        /*System.out.println("------");
        linkedList.deleteHeadNode();
        linkedList.traverse();*/

        //测试删除整个链表
        /*System.out.println("------");
        linkedList.deleteLinkedList();
        linkedList.traverse();*/

        //测试删除链表中重复数据节点
        /*System.out.println("------");
        linkedList.deleteDuplecateV2();
        linkedList.traverse();*/

        //测试获取链表中倒数第k的节点
        /*System.out.println("------");
        Node target = linkedList.getElementByReciprocal(30);
        linkedList.traverse();*/

        //测试
        /*System.out.println("------");
        SinglyLinkedList.traverseReversely(linkedList.getHead());*/

        linkedList.traverse();

        //测试获取链表中倒数第k的节点
        /*System.out.println("------");
        Node mid = linkedList.searchMid();
        System.out.println(mid.data);*/
        //testIsIntersect();

        //测试删除第k的节点
        /*System.out.println("------");
        linkedList.deleteNode(2);
        linkedList.traverse();*/

        //测试删链表内排序
        /*System.out.println("------");
        linkedList.orderLinkedList(Comparator.comparing(Object::toString));
        linkedList.traverse();*/

        //测试链表中是否存在环
        //testHasLoop();
        testDeleteCurrentNode();
    }

    public static void testDeleteCurrentNode() {
        assert 1!=1;
        SinglyLinkedList<String> one = new SinglyLinkedList<>();
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        one.addNode(a);
        one.addNode(b);
        one.addNode(c);
        one.deleteNode(b);
        System.out.println("");

    }

    public static void testHasLoop() {
        SinglyLinkedList<String> one = new SinglyLinkedList<>();
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D", a);
        one.addNode(a);
        one.addNode(b);
        one.addNode(c);
        one.addNode(d);
        boolean hasLoop = one.hasLoop();
        System.out.println(hasLoop);
        System.out.println(one.getLoopLength());
        Node loopStart = one.getLoopStart();
        //one.deleteNode(loopStart);
        System.out.println("");

    }

    public static void testIsIntersect() {
        SinglyLinkedList<String> one = new SinglyLinkedList<>();
        one.addNode("Hello");
        one.addNode("BeiJing");

        SinglyLinkedList<String> two = new SinglyLinkedList<>();
        two.addNode("World");
        two.addNode("Xian");

        Node node = new Node("OK");

        one.insertPosNode(2, node);
        two.insertPosNode(2, node);

        boolean res = SinglyLinkedList.isIntersect(one.getHead(), two.getHead());
        System.out.println(res);

        Node meetNode = SinglyLinkedList.getFirstMeetNode(one.getHead(), two.getHead());
        System.out.println(meetNode.data);
    }

}
