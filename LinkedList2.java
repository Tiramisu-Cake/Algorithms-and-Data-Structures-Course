package com.company;
import java.util.*;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item)
    {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value)
    {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
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
        if (this.find(_value) == null) {
            return false;
        }

        Node node = this.head;

        if (this.find(_value) == node) {
            if (node == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                node.next.prev = null;
                this.head = node.next;
            }
            return true;
        }

        node = node.next;
        while (node != this.tail) {
            if (node.value == _value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return true;
            }
            node = node.next;
        }

        node.prev.next = null;
        this.tail = node.prev;
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
        if (_nodeAfter == null) {
            if (this.head == null && this.tail == null) {
                this.addInTail(_nodeToInsert);
            } else {
                _nodeToInsert.next = this.head;
                _nodeToInsert.prev = null;
                this.head.prev = _nodeToInsert;
                this.head = _nodeToInsert;
            }
        } else {
            Node node = this.head;
            while (node != null) {
                if (node == _nodeAfter) {
                    if (node == this.tail) {
                        this.addInTail(_nodeToInsert);
                    } else {
                        _nodeToInsert.next = node.next;
                        _nodeToInsert.prev = node;
                        node.next.prev = _nodeToInsert;
                        node.next = _nodeToInsert;
                    }
                    break;
                }
                node = node.next;
            }
        }
        // здесь будет ваш код вставки узла после заданного узла

        // если _nodeAfter = null
        // добавьте новый элемент первым в списке
    }
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}
