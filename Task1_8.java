package com.company;

import java.util.ArrayList;

public class Task1_8 {

    public static LinkedList Sum(LinkedList A, LinkedList B) {
        LinkedList C = new LinkedList();

        int n = A.count();
        if (n == B.count()) {
            int [] Arr = new int[n];
            Node node1 = A.head;
            Node node2 = B.head;
            int i = 0;
            while (node1 != null) {
                Arr[i] = node1.value + node2.value;
                i++;
                node1 = node1.next;
                node2 = node2.next;
            }
            for (i = 0; i < n - 1; i++) {
                Node res = new Node(Arr[i]);
                res.next = new Node(Arr[i+1]);
                C.addInTail(res);
            }
            Node res = new Node(Arr[n-1]);
            C.addInTail(res);
        }

        return C;
    }
}
