package com.company;

import java.util.ArrayList;

public class Task1_8 {

    public static LinkedList Sum(LinkedList A, LinkedList B) {
        LinkedList C = new LinkedList();
        Node node1 = A.head;
        Node node2 = B.head;
        while (node1 != null || node2 != null) {
            if (node1 == null && node2 != null || node1 != null && node2 == null) {
                C.clear();
                break;
            }
            Node res = new Node(node1.value + node2.value);
            C.addInTail(res);
            node1 = node1.next;
            node2 = node2.next;
        }

        return C;
    }
}
