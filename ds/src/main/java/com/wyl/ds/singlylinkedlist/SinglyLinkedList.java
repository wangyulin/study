package com.wyl.ds.singlylinkedlist;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Objects;

/**
 * Created by wangyulin on 18/03/2018.
 */
public class SinglyLinkedList<E> {

    private Node<E> head;

    public SinglyLinkedList() {
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * 在链表末尾追加元素
     * @param item
     */
    public void addNode(E item) {
        Node<E> currentNode = new Node(item);
        addNode(currentNode);
    }

    public void addNode(Node node) {
        if(Objects.isNull(head)) {
            head = node;
            return;
        }
        Node tmp = head;
        while(Objects.nonNull(tmp.next)) {
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public boolean insertPosData(int pos, E data) {
        return insertPosNode(pos, new Node(data));
    }

    /**
     * 删除当前节点
     * @param node
     */
    public void deleteNode(Node node) {
        if(Objects.isNull(node)) {
            return;
        }

        if(Objects.isNull(node.next)) {
            Node p = head;
            while(p.next != node) {
                p = p.next;
            }
            p.next = null;
        } else {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

    /**
     * 删除指定index位置的节点
     * @param index 从1开始
     * @return
     */
    public boolean deleteNode(int index) {
        if(index <= 0) {
            throw new IllegalArgumentException();
        }

        if(index == 1) {
            head = head.next;
            return true;
        }

        Node pre = head;
        Node cur = head.next;
        int i = 2;
        while(Objects.nonNull(cur)) {
            if(index == i) {
                pre.next = cur.next;
                return true;
            }
            pre = pre.next;
            cur = cur.next;
            i++;
        }

        return false;
    }

    public void orderLinkedList(Comparator<? super E> c) {
        Node cur = head;
        Node next;

        while(Objects.nonNull(cur)) {
            next = cur.next;
            E tmp;
            while(Objects.nonNull(next)) {
                if(c.compare((E)cur.data, (E)next.data) > 0) {
                    tmp = (E)cur.data;
                    cur.data = next.data;
                    next.data = tmp;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }

    public boolean hasLoop() {
        if(Objects.isNull(head) || Objects.isNull(head.next)) {
            return false;
        }

        Node p = head;
        Node q = head;
        while(Objects.nonNull(p.next) && Objects.nonNull(p.next.next)) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                return true;
            }
        }
        return false;
    }

    public Node getLoopStart() {
        if(Objects.isNull(head) || Objects.isNull(head.next)) {
            return null;
        }

        Node p = head;
        Node q = head;
        while(Objects.nonNull(p.next) && Objects.nonNull(p.next.next)) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                return p;
            }
        }
        return null;
    }

    public int getLoopLength() {
        int length = 0;
        if(Objects.isNull(head) || Objects.isNull(head.next)) {
            return length;
        }

        Node p = head;
        Node q = head;
        while(Objects.nonNull(p.next) && Objects.nonNull(p.next.next)) {
            p = p.next;
            q = q.next.next;
            if(p == q) {
                break;
            }
        }


        while(p.next != q) {
            p = p.next;
            length ++;
        }

        return length + 1;
    }

    public boolean deleteLinkedList() {
        head = null;
        return false;
    }

    /**
     * 删除头节点
     * @return
     */
    public boolean deleteHeadNode() {
        if(Objects.isNull(head)) {
            return false;
        }

        if(Objects.isNull(head.next)) {
            head = null;
            return true;
        }

        Node pre = head;
        Node cur = pre.next;
        head = cur;

        return true;
    }

    /**
     * 删除链表尾节点
     * @return
     */
    public boolean deleteTailNode() {
        if(Objects.isNull(head)) {
            return false;
        }

        if(Objects.isNull(head.next)) {
            head = null;
            return true;
        }

        Node pre = head;
        Node cur = head.next;
        while(Objects.nonNull(cur.next)) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = null;
        return true;
    }

    /**
     * 从链表中删除重复节点
     * 空间换时间
     */
    public void deleteDuplecateV1() {
        Hashtable<E,Integer> dict = new Hashtable<>();
        Node tmp = head;
        Node pre = null;
        while(Objects.nonNull(tmp)) {
            if(dict.containsKey(tmp.data)) {
                pre.next = tmp.next;
            } else {
                dict.put((E) tmp.data, 1);
                pre = tmp;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 从链表中删除重复节点
     * 时间换空间
     */
    public void deleteDuplecateV2() {
        Node p = head;
        while(Objects.nonNull(p)) {
            Node q = p;
            while(Objects.nonNull(q.next)) {
                if(p.data.equals(q.next.data)) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
    }

    /**
     * 插入链表指定位置pos，从1开始,而pos大于size则插入链表末端
     * @param pos
     * @param node
     * @return
     */
    public boolean insertPosNode(int pos, Node<E> node) {
        Objects.requireNonNull(node);
        if (pos < 1) throw new IllegalArgumentException();
        if (Objects.isNull(head)) {
            head = node;
            return true;
        }

        Node tmp = head;
        int index = 1;
        while(Objects.nonNull(tmp.next) && (index++ < (pos - 1))) {
            tmp = tmp.next;
        }

        node.next = tmp.next;
        tmp.next = node;
        return true;
    }

    /**
     * 根据内容查找数据在链表中的节点信息
     * @param data
     * @return
     */
    public Node<E> findNode(E data) {
        Node p = head;
        while(Objects.nonNull(p)) {
            if(p.data.equals(data)) {
                break;
            }
            p = p.next;
        }

        return Objects.isNull(p) ? null : p;
    }

    public Node searchMid() {
        Node p = head;
        Node q = head;

        while(Objects.nonNull(p) && Objects.nonNull(p.next)
                && Objects.nonNull(p.next.next)) {
            p = p.next.next;
            q = q.next;
        }
        return q;
    }

    /**
     * 实现将提供的单链表合并在当前链表末尾
     * @param otherLinkedList
     * @return
     */
    public boolean mergeLinkedList(SinglyLinkedList<E> otherLinkedList) {
        if(Objects.isNull(otherLinkedList)) {
            return true;
        }

        Node p = head;
        while(Objects.nonNull(p.next)) {
            p = p.next;
        }

        p.next = otherLinkedList.head;

        return true;
    }

    /**
     * 获取链表倒数第k的节点
     * @param index
     * @return
     */
    public Node getElementByReciprocal(int index) {
        if (index < 1) {
            throw new IllegalArgumentException();
        }

        Node p = head;
        Node q = head;
        int location = index;
        while(Objects.nonNull(p) && location > 1) {
            p = p.next;
            location--;
        }

        while(Objects.nonNull(p) && Objects.nonNull(p.next)) {
            p = p.next;
            q = q.next;
        }

        return q;
    }

    /**
     * 获取指定位置的节点信息
     * @param index 从1开始
     * @return
     */
    public Node getElement(int index) {

        if(index < 1) {
            throw new IllegalArgumentException("提供索引参数非法!");
        }

        Node cur = head;

        int i = 1;
        while(Objects.nonNull(cur) && i < index) {
            cur = cur.next;
            i++;
        }

        if(Objects.isNull(cur)) {
            return null;
        }

        return cur;
    }

    /**
     * 逆置单链表（递归）
     * @param headNode
     * @return
     */
    private static Node revertByHeadNode(Node headNode) {
        if(Objects.isNull(headNode) || Objects.isNull(headNode.next)) {
            return headNode;
        }

        Node revertHead = revertByHeadNode(headNode.next);
        headNode.next.next = headNode;
        headNode.next = null;
        return revertHead;
    }

    public static SinglyLinkedList revertBySinglyLinkedList(SinglyLinkedList singlyLinkedList) {
        Node newHead = revertByHeadNode(singlyLinkedList.head);
        singlyLinkedList.setHead(newHead);
        return singlyLinkedList;
    }

    public static SinglyLinkedList revertBySinglyLinkedListV2(SinglyLinkedList singlyLinkedList) {
        Node newHead = revertByHeadNodeV2(singlyLinkedList.head);
        singlyLinkedList.setHead(newHead);
        return singlyLinkedList;
    }

    /**
     * 逆置单链表（非递归）
     * @param headNode
     * @return
     */
    private static Node revertByHeadNodeV2(Node headNode) {
        Node pre = headNode;
        Node cur = headNode.next;
        Node tmp;
        pre.next = null;
        while(Objects.nonNull(cur)) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static int getLength(SinglyLinkedList singlyLinkedList) {
        int length = 0;
        if(Objects.isNull(singlyLinkedList) || Objects.isNull(singlyLinkedList.getHead())) {
            return length;
        }

        Node cur = singlyLinkedList.getHead();
        while(Objects.nonNull(cur)) {
            length++;
            cur = cur.next;
        }

        return length;
    }

    /**
     * 从头至尾遍历链表并输出
     */
    public void traverse() {
        Node p = head;
        while(Objects.nonNull(p)) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    /**
     * 从链表尾到头输出
     * @param head
     */
    public static void traverseReversely(Node head) {
        if(Objects.nonNull(head)) {
            traverseReversely(head.next);
            System.out.println(head.data);
        }
    }

    /**
     * 从头节点开始遍历输出所有节点信息
     * @param head
     */
    public static void traverse(Node head) {
        Node p = head;
        while(Objects.nonNull(p)) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    public static boolean isIntersect(Node head1, Node head2) {
        if(Objects.isNull(head1) || Objects.isNull(head2)) {
            return false;
        }

        Node tail1 = head1;
        while(Objects.nonNull(tail1.next)) {
            tail1 = tail1.next;
        }

        Node tail2 = head2;
        while(Objects.nonNull(tail2.next)) {
            tail2 = tail2.next;
        }

        return tail1 == tail2;
    }

    public static Node getFirstMeetNode(Node head1, Node head2) {
        if(Objects.isNull(head1) || Objects.isNull(head2)) {
            return null;
        }

        Node tail1 = head1;
        int length1 = 1;
        while(Objects.nonNull(tail1.next)) {
            tail1 = tail1.next;
            length1++;
        }

        Node tail2 = head2;
        int length2 = 1;
        while(Objects.nonNull(tail2.next)) {
            tail2 = tail2.next;
            length2++;
        }

        if(tail1 != tail2) {
            return null;
        }

        tail1 = head1;
        tail2 = head2;

        if(length1 > length2) {
            int d = length1 - length2;
            while(d > 0) {
                tail1 = tail1.next;
                d--;
            }
        }

        if(length1 < length2) {
            int d = length2 - length1;
            while(d > 0) {
                tail2 = tail2.next;
                d--;
            }
        }

        while(tail1 != tail2) {
            tail1 = tail1.next;
            tail2 = tail2.next;
        }

        return tail1;
    }

    public boolean isEmpty() {
        return Objects.isNull(head);
    }
}