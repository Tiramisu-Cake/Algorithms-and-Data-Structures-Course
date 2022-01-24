package com.company;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.LinkRef;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class LinkedListTest {


    public Random rand = new Random();
    public LinkedList S = new LinkedList();

    @Test
    public void findAll() {
        //создаем тестовые данные
        S = new LinkedList();
        int i = 0;
        while (i < 100) {
            S.addInTail(new Node(rand.nextInt(5)));
            i++;
        }
        ArrayList<Node> res = new ArrayList<>();

        //Проверям, что для непустого списка вернется непустой массив
        res = S.findAll(rand.nextInt(5));
        Assert.assertFalse(res.isEmpty());
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        //Проверяем, что для пустого списка вернется пустой массив
        S = new LinkedList();
        res = S.findAll(rand.nextInt());
        Assert.assertTrue(res.isEmpty());
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        //Проверяем список из одного элемента, когда в нем есть искомое значение
        S = new LinkedList();
        int k = rand.nextInt(5);
        S.addInTail(new Node(k));
        res = S.findAll(k);
        Assert.assertTrue(res.get(0).value == k);
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        //Проверяем список из одного элемента, когда в нем нет искомого значения
        S = new LinkedList();
        S.addInTail(new Node(k));
        int l = rand.nextInt(5);
        res = S.findAll(l+1);
        Assert.assertTrue(res.isEmpty());
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

    }

    @Test
    public void remove() {
        //создаем тестовые данные
        S = new LinkedList();
        int i = 0;
        while (i < 100) {
            S.addInTail(new Node(rand.nextInt(5)));
            i++;
        }
        boolean res = false;

        //Проверяем, что для непустого списка что-то удалится
        res = S.remove(rand.nextInt(5));
        assertTrue(res);
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        //Проверяем, что для пустого списка ничего не удалится
        S = new LinkedList();
        res = S.remove(rand.nextInt(5));
        Assert.assertFalse(res);

        //Проверяем список из одного элемента, когда в нем есть искомое значение
        int k = rand.nextInt(5);
        S.addInTail(new Node(k));
        res = S.remove(k);
        Assert.assertTrue(res);
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        //Проверяем список из одного элемента, когда в нем нет искомого значения
        int l = rand.nextInt(5);
        S.addInTail(new Node(l+1));
        res = S.remove(l);
        Assert.assertFalse(res);
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

    }

    @Test
    public void removeAll() {
        //создаем тестовые данные
        S = new LinkedList();
        int i = 0;
        while (i < 100) {
            S.addInTail(new Node(rand.nextInt(5)));
            i++;
        }

        // Проверяем удаление из хвоста для непустого списка
        i = 0;
        int j = 10;
        while (i < j) {
            S.addInTail(new Node(6));
            i++;
        }
        S.removeAll(6);
        Assert.assertEquals(S.count(), 100);
        Assert.assertNull(S.find(6));
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        // Проверяем непустой список
        int k = rand.nextInt(5);
        S.removeAll(k);
        Assert.assertNull(S.find(k));
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        // Проверяем список из одного элемента, когда в нем есть искомое значение
        S = new LinkedList();
        S.addInTail(new Node(k));
        S.removeAll(k);
        Assert.assertNull(S.find(k));
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        // Проверяем список из одного элемента, когда в нем нет искомого значения
        S = new LinkedList();
        S.addInTail(new Node(k));
        S.removeAll(k+1);
        Assert.assertNull(S.find(k+1));
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);
    }

    @Test
    public void clear() {
        //создаем тестовые данные
        S = new LinkedList();
        int i = 0;
        while (i < 100) {
            S.addInTail(new Node(rand.nextInt(5)));
            i++;
        }

        // Проверяем на непустом списке
        S.clear();
        int n = S.count();
        Assert.assertEquals(0,n);
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        // Проверяем на пустом списке
        S = new LinkedList();
        S.clear();
        n = S.count();
        Assert.assertEquals(0,n);
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        // Проверяем на списке из одного элемента
        S = new LinkedList();
        S.addInTail(new Node(rand.nextInt(5)));
        S.clear();
        n = S.count();
        Assert.assertEquals(0,n);
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);
    }

    @Test
    public void count() {
        //создаем тестовые данные
        S = new LinkedList();
        int i = 0;
        int k = 1000000;
        while (i < k) {
            S.addInTail(new Node(rand.nextInt(5)));
            i++;
        }
        int res = 0;

        // Проверяем на непустом списке
        res = S.count();
        Assert.assertEquals(k,res);

        // Проверяем на пустом списке
        S = new LinkedList();
        res = S.count();
        Assert.assertEquals(0,res);

        // Проверяем на списке из одного элемента
        S.addInTail(new Node(rand.nextInt(5)));
        res = S.count();
        Assert.assertEquals(1,res);
    }

    @Test
    public void insertAfter() {
        // создаем тестовые данные
        S = new LinkedList();
        int i = 0;

        // возьмем случайный узел, после которого будет вставка
        int k = rand.nextInt(99);
        Node n = new Node(0);
        while (i < 100) {
            Node s = new Node(rand.nextInt(5));
            S.addInTail(s);
            if (i == k) {
                n = s;
            }
            if (i == k + 1) {
                n.next = s;
            }
            i++;
        }

        // Вставляем узел, которого гарантированно не было в списке

        // Проверяем на непустом списке
        S.insertAfter(n, new Node(10));
        Assert.assertNotNull(S.find(10));

        // проверка, что новый узел вставился именно после заданного:
        Assert.assertEquals(S.find(n.value).value, n.value);
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);

        // Проверяем на пустом списке
        S = new LinkedList();
        S.insertAfter(n, new Node(10));
        // т.к. список пуст, а n != null, то вставка не должна произойти
        Assert.assertNull(S.find(10));
        Assert.assertNull(S.head);
        Assert.assertNull(S.tail);

        // Проверяем на списке из одного элемента
        S = new LinkedList();
        n = new Node(1);
        S.addInTail(n);
        S.insertAfter(n, new Node(10));
        Assert.assertNotNull(S.find(10));

        // проверка, что новый узел вставился именно после заданного:
        Assert.assertEquals(S.find(n.value).value, n.value);
        Assert.assertNotNull(S.head);
        Assert.assertNotNull(S.tail);
    }
}