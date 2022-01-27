package com.company;

public class Task4_5 {
    // Сбалансированные строки
    public static boolean BracketBalance(String S) { // считаем, что строка состоит только из скобок или пустая
        boolean res = true; // пустую строку считаем сбалансированной
        Stack<Character> Stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            Stack.push(S.charAt(i)); // будем рассматривать строку с конца
        }
        Character C;
        int close = 0;
        while (Stack.size() > 0) {
            C = Stack.pop();
            // считаем закрывающие скобки до первой открывающей или до конца стека
            while(C == Character.valueOf(')') || C == null) {
                close++;
                C = Stack.pop();
            }
            int open = 0;
            // считаем открывающие скобки до первой закрывающей или до конца стека
            while(C == Character.valueOf('(') || C == null) {
                open++;
                C = Stack.pop();
            }
            if (close == open) { // если открывающий и закрывающих одинаковое кол-во, то считаем дальше
                close = 1;
            }
            else { // если нет, то строка не сбалансирована и дальше можно не проверять
                res = false;
                break;
            }
        }
        return res;
    }

    // Вычисление выражения в стеке
    public static int Calc(Stack S) { //считаем входной стэк корректным
        Stack S2 = new Stack();
        S2.push(S.pop()); // берем первое число
        // теперь в исходном стеке идет чередование число/знак
        while (S.size() > 1) { // cчитаем, что последний элемент в стэке - это всегда знак '='
            S2.push(S.pop());
            char operation = (char) S.pop();
            if (operation == '*') {
                S2.push((int) S2.pop() * (int) S2.pop());
            }
            if (operation == '+') {
                S2.push((int) S2.pop() + (int) S2.pop());
            }
        }
        return (int) S2.pop();
    }
}
