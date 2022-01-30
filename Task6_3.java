package com.company;

public class Task6_3 {
    public static boolean Palindrom(String S) {
        Deque Q = new Deque();
        for (int i = 0; i < S.length(); i++) { // заполняем очередь строкой по-элементно
            Q.addTail(S.charAt(i));
        }
        for (int i = 0; i < S.length() / 2; i++) { // проверяем первый и последний элемент очереди на равенство
            if ((char) Q.removeFront() != (char) Q.removeTail()) {
                return false;
            }
        }
        return true;

    }
}
