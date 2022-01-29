package com.company;

import java.util.*;


class Node<T>
{
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T>
{
    public Node<T> head, tail;
    private boolean _ascending;
    private int count;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        count = 0;
        _ascending = asc;
    }
    private void addInTail(Node _item)
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

    public int compare(T v1, T v2)
    {
        String v1_c = v1.getClass().getSimpleName();
        String v2_c = v1.getClass().getSimpleName();
        if (v1_c.equals("String")) {
            if ( ((String) v1).compareTo((String) v2) < 0) {
                return -1;
            }
            if (((String) v1).compareTo((String) v2) > 0) {
                return 1;
            }
        } else {
            if ((int) v1 < (int) v2) {
                return -1;
            }
            if ((int) v1 > (int) v2) {
                return 1;
            }
        }
        return 0;
    }

    public void add(T value) {
        count++;
        Node res = new Node(value);
        if (this.head == null) {
            this.addInTail(res);
            return;
        }
        Node node = this.head;
        int x;
        if (_ascending == true) {
            x = 1;
        } else {
            x = -1;
        }
        while (compare(value, (T) node.value) == x) {
            node = node.next;
            if (node == null) {
                break;
            }
        }

        if (node != null) {
            if (node != this.head) {
                node.prev.next = res;
                res.prev = node.prev;
                node.prev = res;
                res.next = node;
            } else {
                this.head = res;
                res.next = node;
                node.prev = res;
            }
        } else {
            this.addInTail(res);
        }
    }

    public Node<T> find(T val)
    {
        Node node = this.head;
        while (node != null) {
            if (node.value == val) {
                return node;
            }
            node = node.next;
        }
        return null; // здесь будет ваш код
    }

    public void delete(T val)
    {
        Node node = find(val);
        if (node != null) {
            if (this.count == 1) {
                this.clear(_ascending);
            } else if (node == this.head) {
                this.head.next.prev = null;
                this.head = this.head.next;
            } else if (node == this.tail) {
                this.tail.prev.next = null;
                this.tail = this.tail.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            count--;
        }
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    public int count()
    {
        return count; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}
