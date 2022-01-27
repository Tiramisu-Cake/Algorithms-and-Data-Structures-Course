package com.company;

public class Task5_3 {
    public static void rotate(Queue Q, int N) {
        for (int i = 0; i < N; i++) {
            Q.enqueue(Q.dequeue());
        }
    }
}
