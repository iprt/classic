package org.iproute.p002_add_two_numbers;

/**
 * ListNode
 *
 * @author tech@intellij.io
 * @since 2022/2/28
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
