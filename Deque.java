package com.company;
import java.util.*;

public class Deque<T>
{
    private Stack S1;
    private Stack S2;
    public Deque()
    {
        S1 = new Stack();
        S2 = new Stack();
    }

    public void addFront(T item)
    {
        S1.push(item);

    }

    public void addTail(T item)
    {
        if (S1.size() == 0) {
            S1.push(item);
        } else {
            S2.push(item);
        }
    }

    public T removeFront()
    {
        if (S1.size() == 0) { // если голова пуста, то брать нечего
            return null;
        }
        T res = (T) S1.pop(); // берем элемент из головы, результат метода
        while (S2.size() > 0) {
            S1.push(S2.pop()); // перемещаем все в первый стек
        }
        T head = (T) S1.pop(); // последний перемещенный будет новой головой
        if (head != null) { // если она не пуста
            while (S1.size() > 0) {
                S2.push(S1.pop()); // то перемещаем все назад
            }
            S1.push(head); // кроме головы
        }
        return res;
    }

    public T removeTail()
    {
        if (S2.size() == 0) {
            return (T) S1.pop();
        }
        return (T) S2.pop();
    }

    public int size()
    {
        return S1.size() + S2.size();
    }
}
class Stack<T>
{
    private Node head;
    private Node tail;
    private int count;

    public Stack()
    {
        head = new Node(0);
        tail = new Node(0);
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
