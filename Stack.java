package com.company;
import java.util.*;

public class Stack<T>
{
    private DummyNode head;
    private DummyNode tail;
    public int count;

    public Stack()
    {
        head = new DummyNode(0);
        tail = new DummyNode(0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        count = 0;
    }

    public int size()
    {
        return this.count;
    }

    public T pop()
    {
        T res = this.peek();
        if (res == null) {
            return null;
        }
        this.tail.prev.prev.next = this.tail;
        this.tail.prev = this.tail.prev.prev;
        count--;
        return res;
    }

    public void push(T val)
    {
        Node _item = new Node(val);
        if (head.next == tail) {
            this.head.next = _item;
            _item.prev = this.head;
        } else {
            this.tail.prev.next = _item;
            _item.prev = this.tail.prev;
        }
        _item.next = this.tail;
        this.tail.prev = _item;
        count++;
    }

    public T peek()
    {
        if (this.head.next == this.tail) {
            return null;
        }
        Object res = this.tail.prev.value;
        return (T) res;
    }
}
class Node<T>
{
    public T value;
    public Node next;
    public Node prev;


    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}
class DummyNode<T> extends Node
{
    public DummyNode(T _value) {
        super(_value);
    }
}