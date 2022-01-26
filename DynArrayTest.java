package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DynArrayTest {
    public DynArray<Integer> A = new DynArray<Integer>(Integer.class);
    Random rand = new Random();

    @Test
    public void insert() {

        // Вставка элемента, когда в итоге размер буфера не превышен (проверяем также размер буфера):
        // Тестовые данные
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 100; i++) {
            A.append(rand.nextInt());
        }
        int buff = A.capacity; //128
        int A_length = A.count; // 100
        // Тест
        A.insert(rand.nextInt(), rand.nextInt(100));
        Assert.assertEquals(buff, A.capacity);
        Assert.assertEquals(101,A.count);

        // Вставка элемента, когда в результате превышен размер буфера
        // Проверяем также корректное изменение размера буфера
        // Тестовые данные
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 128; i++) {
            A.append(rand.nextInt());
        }
        buff = A.capacity; //128
        A_length = A.count; // 128

        //Тест
        A.insert(rand.nextInt(), rand.nextInt(100));
        Assert.assertEquals(2*buff, A.capacity);
        Assert.assertEquals(129,A.count);

    }

    @Test
    // Попытка вставки элемента в недопустимую позицию
    public void InsertIllegalArgumentTest() throws IllegalArgumentException {
        // Тестовые данные
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 100; i++) {
            A.append(rand.nextInt());
        }
        try {
            A.insert(rand.nextInt(), A.count * rand.nextInt());
            Assert.fail("Expected exception!");
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void remove() {

        // удаление элемента, когда в результате размер буфера остаётся прежним
        // проверяем также размер буфера

        // Тестовые данные
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 100; i++) {
            A.append(rand.nextInt());
        }
        int buff = A.capacity; //128
        int A_length = A.count; // 100

        // Тест
        A.remove(rand.nextInt(100));
        Assert.assertEquals(99,A.count);
        Assert.assertEquals(buff,A.capacity);
        Assert.assertTrue(A.capacity >= 16);


        // удаление элемента, когда в результате понижается размер буфера
        // проверяем также корректное изменение размера буфера
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 64; i++) {
            A.append(rand.nextInt());
        }
        A.capacity = 131;
        buff = A.capacity; // 131
        A_length = A.count; // 64

        // Тест
        A.remove(rand.nextInt(64));
        Assert.assertEquals(63,A.count);
        int new_buff = (int) ((buff * 1.0)/1.5);
        Assert.assertEquals(new_buff, A.capacity);
        Assert.assertTrue(A.capacity >= 16);
    }

    @Test
    // Попытка удаления элемента в недопустимой позиции
    public void removeIllegalArgumentTest() throws IllegalArgumentException {
        // Тестовые данные
        A = new DynArray<Integer>(Integer.class);
        for (int i = 0; i < 100; i++) {
            A.append(rand.nextInt());
        }
        try {
            A.remove(A.count * rand.nextInt());
            Assert.fail("Expected exception!");
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
        finally {
            Assert.assertTrue(A.capacity >= 16);
        }
    }
}