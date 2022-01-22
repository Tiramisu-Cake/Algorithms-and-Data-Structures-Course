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

    public boolean remove(int _value)
    {
        boolean res = false;
        Node node = this.head;
        if (node != null) {
            if (node.value == _value) {
                if (this.count() == 1) {
                    this.head = null;
                    this.tail = null;
                } else {
                    this.head = node.next;
                }
                res = true;
            }
        }
        if (res == false) {
            while (node != null) {
                if (node.next == null) {
                    break;
                }
                if (node.next.value == _value) {
                    node.next = node.next.next;
                    res = true;
                    break;
                }
                node = node.next;
            }
        }
        return res;
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
            this.head = _nodeToInsert;
            this.head.next = node;
        } else while (node != null) {
            if (node == _nodeAfter) {
                Node node2 = node.next;
                node.next = _nodeToInsert;
                _nodeToInsert.next = node2;
                break;
            }
            node = node.next;
        }
        // здесь будет ваш код вставки узла после заданного

        // если _nodeAfter = null ,
        // добавьте новый элемент первым в списке
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
