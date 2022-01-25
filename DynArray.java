package com.company;

import java.lang.reflect.Array;

public class DynArray<T>
{
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz)
    {
        clazz = clz;
        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity)
    {
        if (new_capacity >= 0) {
            if(count == 0) {
                array = (T[]) Array.newInstance(this.clazz, new_capacity);
            } else  {
                int j;
                if (new_capacity < count) {
                    j = new_capacity;
                } else {
                    j = count;
                }
                T [] array_copy = (T[]) Array.newInstance(this.clazz, new_capacity);
                for (int i = 0; i<j; i++) {
                    array_copy[i] = array[i];
                }
                array = array_copy;
                count = j;
            }
        } else {
            throw new IllegalArgumentException("");
        }
        capacity = new_capacity;
    }

    public T getItem(int index)
    {
        if (index >= 0 && index < count) {
            return array[index];
        }
        else {
            throw new IllegalArgumentException("");
        }
    }

    public void append(T itm)
    {
        if (capacity == count) {
            this.makeArray(2 * capacity);
        }
        this.array[count] = itm;
        count++;
    }

    public void insert(T itm, int index)
    {
        if (!(index >= 0 && index <= count)) {
            throw new IllegalArgumentException("Illegal Argument: " + index);
        }
        if (capacity == count) {
            this.makeArray(2 * capacity);
        }
        for (int i = count; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = itm;
        count++;
    }

    public void remove(int index)
    {
        if (!(index >= 0 && index <= count)) {
            throw new IllegalArgumentException("Illegal Argument: " + index);
        }

        for (int i = index; i < count; i++) {
            array[i] = array[i+1];
        }
        count--;
        int new_capacity = (int) ((capacity * 1.0)/1.5);
        if (new_capacity < 16) {
            new_capacity = 16;
        }
        if (count <= new_capacity) {
            this.makeArray(new_capacity);
        }
    }

}
