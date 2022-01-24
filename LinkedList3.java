package com.company;

import java.util.ArrayList;

public class LinkedList3 extends LinkedList2 {
    private Node head;
    private Node tail;

    public LinkedList3()
    {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        //return head;
    }
    public void addInTail(Node _item) {
        if (head.next == tail) {
            this.head.next = _item;
            _item.prev = this.head;
        } else {
            this.tail.prev.next = _item;
            _item.prev = this.tail.prev;
        }
        _item.next = this.tail;
        this.tail.prev = _item;
        System.out.println(_item.next == this.tail);
        System.out.println(_item == this.tail.prev);
        /*

        System.out.println(_item.prev == this.head);

        System.out.println(_item == this.head.next);

         */
    }
    public void printList() {
        Node node = this.head.next;
        while (node != this.tail) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public Node find(int _value)
    {
        Node node = this.head.next;
        while (node != this.tail) {
            if (node.value == _value)
                return node;
            node = node.next;
        }
        return null;
    }
    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head.next;
        while (node != this.tail) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value)
    {
        Node node = this.find(_value);
        if (node == null) {
            return false;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        return true;
    }
    public void removeAll(int _value)
    {
        boolean res = this.remove(_value);
        while (res == true) {
            res = this.remove(_value);
        }
    }

    public void clear()
    {
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int count()
    {
        int res = 0;
        Node node = this.head.next;
        while (node != this.tail) {
            res++;
            node = node.next;
        }
        return res;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            _nodeToInsert.next = this.head.next;
            _nodeToInsert.prev = this.head;
            this.head.next.prev = _nodeToInsert;
            this.head.next = _nodeToInsert;
        } else {
            Node node = this.head.next;
            while(node != this.tail) {
                if (node == _nodeAfter) {
                    _nodeToInsert.next = node.next;
                    _nodeToInsert.prev = node;
                    node.next.prev = _nodeToInsert;
                    node.next = _nodeToInsert;
                    break;
                }
                node = node.next;
            }
        }
    }
}
