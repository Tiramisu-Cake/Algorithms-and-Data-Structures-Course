package com.company;
public class HashTable
{
    public int size;
    public int step;
    public String [] slots;

    public HashTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
    }

    public int hashFun(String value)
    {
        byte [] hash = value.getBytes();
        int ind = 0;
        for(byte b : hash) {
            ind += b;
        }
        return ind % size;
    }

    public int seekSlot(String value)
    {
        int ind = this.hashFun(value);
        if (slots[ind] == null) {
            return ind;
        }
        for (int i = ind; i < size; i += step) {
            if (slots[i] == null) {
                return i;
            }
        }
        for (int i = 0; i < ind; i += step) {
            if (slots[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int put(String value)
    {
        int ind = this.seekSlot(value);
        if (ind != -1) {
            slots[ind] = value;
        }
        return ind;
    }

    public int find(String value)
    {
        int ind = hashFun(value);
        if (slots[ind].equals(value)) {
            return ind;
        }
        for (int i = ind; i < size; i += step) {
            if (slots[i].equals(value)) {
                return i;
            }
        }
        for (int i = 0; i < ind; i += step) {
            if (slots[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }
}
