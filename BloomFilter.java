package com.company;

public class BloomFilter
{
    public int filter_len;
    int BitArr;

    public BloomFilter(int f_len)
    {
        filter_len = f_len;
        int BitArr = 0;
    }

    public int hash1(String str1)
    {
        int code = 0;
        for(int i=0; i<str1.length(); i++)
        {
            code = code * 17;
            code += (int) str1.charAt(i);
            code = code % filter_len;
        }
        return code;
    }
    public int hash2(String str1)
    {
        int code = 0;
        for(int i=0; i<str1.length(); i++)
        {
            code = code * 223;
            code += (int) str1.charAt(i);
            code = code % filter_len;
        }
        return code;
    }

    public void add(String str1)
    {
        int x = (int) Math.pow(2, hash1(str1));
        int y = (int) Math.pow(2, hash2(str1));
        BitArr = BitArr | x;
        BitArr = BitArr | y;
    }

    public boolean isValue(String str1)
    {
        int hash1 = 1 & (BitArr >> hash1(str1));
        int hash2 = 1 & (BitArr >> hash1(str1));
        return hash1 == 1 && hash2 == 1;
    }
}