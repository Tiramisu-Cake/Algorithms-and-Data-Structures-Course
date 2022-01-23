package com.company;

import java.util.*;

public class LinkedList
{
    public Node head;
    public Node tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null) {
            this.head = item;
        } else {
            this.tail.next = item;
        }
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {

        if (this.find(_value) == null) {
            return false;
        }

        Node node = this.head;

        if (this.find(_value) == node) {
            if (node == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = node.next;
            }
            return true;
        }

        while (node.next != this.tail) {
            if (node.next.value == _value) {
                node.next = node.next.next;
                return true;
            }
            node = node.next;
        }

        node.next = null;
        this.tail = node;
        return true;
    }

    public void removeAll(int _value) {
        boolean res = this.remove(_value);
        while (res == true) {
            res = this.remove(_value);
        }
    }

    public void clear()
    {
        this.head = null;
        this.tail = null;
    }

    public int count()
    {
        int res = 0;
        Node node = this.head;
        while (node != null) {
            res++;
            node = node.next;
        }
        return res;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        Node node = this.head;
        if (_nodeAfter == null) {
            if (this.head == null && this.tail == null) {
                this.head = _nodeToInsert;
                this.tail = _nodeToInsert;
                this.head.next = null;
                this.tail.next = null;
            } else {
                node = this.head.next;
                this.head = _nodeToInsert;
                this.head.next = node;
            }
        } else {
            while (node != null) {
                if (node == _nodeAfter) {
                    Node node2 = node.next.next;
                    node.next = _nodeToInsert;
                    node.next.next = node2;
                    break;
                }
                node = node.next;
            }
        }
    }

}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}
