package com.company;

public class Task6_3 {
    public static boolean Palindrom(String S) {
        Deque<Character> Q = new Deque();
        for (int i = 0; i < S.length(); i++) { // заполняем очередь строкой по-элементно
            Q.addTail(S.charAt(i));
        }
        for (int i = 0; i < S.length() / 2; i++) { // проверяем первый и последний элемент очереди на равенство
            if (Q.removeFront().compareTo(Q.removeTail()) != 0) {
                return false;
            }
        }
        return true;

    }
}
