package com.company;

public class PowerSet
{
    public String [] slots;
    public int count;
    public int capacity;

    public PowerSet()
    {
        capacity = 20000;
        count = 0;
        slots = new String[capacity];
    }
    public int hashFun(String value)
    {
        byte [] hash = value.getBytes();
        int ind = 0;
        for(byte b : hash) {
            ind += b;
        }
        return ind % capacity;
    }

    public int size()
    {
        return count;
    }


    public void put(String value) {
        if (this.get(value) == false) {
            count++;
            int ind = hashFun(value);
            if (slots[ind] == null) {
                slots[ind] = value;
                return;
            }
            for (int i = 1; ind + i * i < capacity; i++) {
                if (slots[ind + i * i] == null) {
                    slots[ind + i * i] = value;
                    return;
                }
            }
            for (int i = 1; ind - i * i >= 0; i++) {
                if (slots[ind - i * i] == null) {
                    slots[ind - i * i] = value;
                    return;
                }
            }
            for (int i = 2; ind + i < capacity; i++) {
                if (slots[ind + i] == null) {
                    slots[ind + i] = value;
                    return;
                }
            }
            for (int i = 2; ind - i >= 0; i++) {
                if (slots[ind - i] == null) {
                    slots[ind - i] = value;
                    return;
                }
            }
            slots[ind] = value;
            count--;
        }
    }

    public boolean get(String value)
    {
        int ind = hashFun(value);
        if (slots[ind] != null) {
            if (slots[ind].equals(value)) {
                return true;
            }

        }
        for (int i = 1; ind + i * i < capacity; i++) {
            if (slots[ind + i * i] != null) {
                if(slots[ind + i * i].equals(value)) {
                    return true;
                }
            }
        }
        for (int i = 1; ind - i * i >= 0; i++) {
            if (slots[ind - i * i] != null) {
                if(slots[ind - i * i].equals(value)) {
                    return true;
                }
            }
        }
        for (int i = 2; ind + i < capacity; i++) {
            if (slots[ind + i] != null) {
                if(slots[ind + i].equals(value)) {
                    return true;
                }
            }
        }
        for (int i = 2; ind - i >= 0; i++) {
            if (slots[ind - i] != null) {
                if(slots[ind - i].equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean remove(String value)
    {
        count--;
        int ind = hashFun(value);
        if (slots[ind] != null) {
            if (slots[ind].equals(value)) {
                slots[ind] = null;
                return true;
            }

        }
        for (int i = 1; ind + i * i < capacity; i++) {
            if (slots[ind + i * i] != null) {
                if(slots[ind + i * i].equals(value)) {
                    slots[ind + i * i] = null;
                    return true;
                }
            }
        }
        for (int i = 1; ind - i * i >= 0; i++) {
            if (slots[ind - i * i] != null) {
                if(slots[ind - i * i].equals(value)) {
                    slots[ind - i * i] = null;
                    return true;
                }
            }
        }
        for (int i = 2; ind + i < capacity; i++) {
            if (slots[ind + i] != null) {
                if(slots[ind + i].equals(value)) {
                    slots[ind + i] = null;
                    return true;
                }
            }
        }
        for (int i = 2; ind - i >= 0; i++) {
            if (slots[ind - i] != null) {
                if(slots[ind - i].equals(value)) {
                    slots[ind - i] = null;
                    return true;
                }
            }
        }
        count++;
        return false;
    }

    public PowerSet min(PowerSet set1, PowerSet set2) {
        int n1 = set1.size();
        int n2 = set2.size();
        if (n1 > n2) {
            return set2;
        }
        return set1;
    }
    public PowerSet max(PowerSet set1, PowerSet set2) {
        int n1 = set1.size();
        int n2 = set2.size();
        if (n1 > n2) {
            return set1;
        }
        return set2;
    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet I = new PowerSet();
        PowerSet Min = min(this, set2);
        PowerSet Max = max(this, set2);
        int n = Min.capacity;
        for (int i = 0; i < n; i++) {
            if (Min.slots[i] != null) {
                if (Max.get(Min.slots[i])) {
                    I.put(Min.slots[i]);
                }
            }
        }

        return I;
    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet U = new PowerSet();
        for (int i = 0; i < this.capacity; i++) {
            if (this.slots[i] != null) {
                U.put(this.slots[i]);
            }
        }
        for (int i = 0; i < set2.capacity; i++) {
            if (set2.slots[i] != null) {
                U.put(set2.slots[i]);
            }
        }

        return U;
    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet D = new PowerSet();
        for (int i = 0; i < this.capacity; i++) {
            if (this.slots[i] != null) {
                if (set2.get(this.slots[i]) == false) {
                    D.put(slots[i]);
                }
            }
        }

        return D;
    }

    public boolean isSubset(PowerSet set2)
    {
        int C = set2.capacity;
        for (int i = 0; i < C; i++) {
            if (set2.slots[i] != null) {
                if (this.get(set2.slots[i]) == false) {
                    return false;
                }
            }
        }
        return true;
    }
}
