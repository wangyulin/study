package com.wyl.ds.singlylinkedlist;

/**
 * Created by wangyulin on 18/03/2018.
 */
public class Node<E> {

    E data;
    Node<E> next = null;

    public Node(E data) {
        this(data, null);
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

}
