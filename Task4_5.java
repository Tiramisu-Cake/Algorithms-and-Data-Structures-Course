package com.company;

public class Task4_5 {
    // Сбалансированные строки
    public static boolean BracketBalance(String S) { // считаем, что строка состоит только из скобок или пустая
        if (S.length() % 2 != 0) { // если число символов нечетно, то проверять нечего
            return false;
        }
        boolean res = true; // пустую строку считаем сбалансированной
        Stack<Character> Stack = new Stack();
        for (int i = 0; i < S.length(); i++) { // идем по строке сначала
            if (S.charAt(i) == Character.valueOf('(')) {
                Stack.push(S.charAt(i)); // если скобка открывающая, добавляем в стек
            } else { // если закрывающая, то проверяем стек
                // если стек не пуст, то мы проверкой условия удаляем соответсвующую открывающую скобку
                if (Stack.pop() != Character.valueOf('(')) {
                    // если он пуст, то у закрывающей скобки не нашлось той, которая ее открывает
                    res = false;
                    break;
                }
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
