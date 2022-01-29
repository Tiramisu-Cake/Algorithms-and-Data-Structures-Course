package com.company;
public class Queue2<T> {
    private Stack S1;
    private Stack S2;

    public Queue2()
    {
        S1 = new Stack();
        S2 = new Stack(); 
    }

    public void enqueue(T item) {
        S1.push(item);
    }

    public T dequeue() {
        if (S2.size() == 0) {
            while (S1.size() > 0) {
                S2.push(S1.pop());
            }
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
    public int count;

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


