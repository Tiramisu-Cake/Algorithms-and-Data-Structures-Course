package com.company;
import java.util.*;

public class Queue<T>
{
    private Node head;
    private Node tail;
    private int count;

    public Queue()
    {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        count = 0;
    }

    public void enqueue(T item)
    {
        Node _item = new Node(item);
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

    public T dequeue()
    {
        if (head.next == tail) {
            return null;
        }
        T res = (T) head.next.value;
        head.next.next.prev = head;
        head.next = head.next.next;
        count--;
        return res;
    }

    public int size()
    {
        return count;
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