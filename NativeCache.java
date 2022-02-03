package com.company;

import java.lang.reflect.Array;

public class NativeCache<T>
{
    public int size;
    public String [] slots;
    public T [] values;
    public int [] hits;
    public int count;

    public NativeCache(int sz, Class clazz)
    {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        hits = new int[size];
        count = 0;
    }

    public int hashFun(String key)
    {
        byte [] hash = key.getBytes();
        int ind = 0;
        for(byte b : hash) {
            ind += b;
        }
        return ind % size;
    }

    public int collizion(String key, boolean reason) {
        int ind = hashFun(key);
        if (reason) {
            if (slots[ind] == null) {
                return ind;
            }
            for (int i = 1; ind + i * i < size; i++) {
                if (slots[ind + i * i] == null) {
                    return ind + i * i;
                }
            }
            for (int i = 1; ind - i * i >= 0; i++) {
                if (slots[ind - i * i] == null) {
                    return ind - i * i;
                }
            }
            for (int i = 2; ind + i < size; i++) {
                if (slots[ind + i] == null) {
                    return ind + i;
                }
            }
            for (int i = 2; ind - i >= 0; i++) {
                if (slots[ind - i] == null) {
                    return ind - i;
                }
            }
        } else {
            if (slots[ind] != null) {
                if (slots[ind].equals(key)) {
                    return ind;
                }
            }
            for (int i = 1; ind + i * i < size; i++) {
                if (slots[ind + i * i] != null) {
                    if (slots[ind + i * i].equals(key)) {
                        return ind + i * i;
                    }
                }
            }
            for (int i = 1; ind - i * i >= 0; i++) {
                if (slots[ind - i * i] != null) {
                    if (slots[ind - i * i].equals(key)) {
                        return ind - i * i;
                    }
                }
            }
            for (int i = 2; ind + i < size; i++) {
                if (slots[ind + i] != null) {
                    if (slots[ind + i].equals(key)) {
                        return ind + i;
                    }
                }
            }
            for (int i = 2; ind - i >= 0; i++) {
                if (slots[ind - i] != null) {
                    if (slots[ind - i].equals(key)) {
                        return ind - i;
                    }
                }
            }
        }
        return -1;
    }
    public void push() {
        int min = hits[0];
        int k = 0;
        for (int i = 1; i < size; i++) {
            if (hits[i] < min) {
                min = hits[i];
                k = i;
            }
        }
        slots[k] = null;
        values[k] = null;
        hits[k] = 0;
        count--;
    }
    public void put(String key, T value) {
        if (count == size) {
            this.push();
        }
        count++;
        int ind = this.collizion(key, true);
        slots[ind] = key;
        values[ind] = value;
        hits[ind] = 0;
    }

    public T get(String key)
    {
        int ind = this.collizion(key, false);
        if (ind == - 1) {
            return null;
        }
        hits[ind]++;
        return values[ind];
    }
    public boolean isKey(String key)
    {
        int ind = this.collizion(key, false);
        return ind != -1;
    }
}
