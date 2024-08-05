package org.iproute.classic.tree.test;


import org.iproute.classic.skiplist.SkipList;
import org.iproute.classic.skiplist.SkipNode;
import org.junit.jupiter.api.Test;

/**
 * SkipListTest
 *
 * @author tech@intellij.io
 */
public class SkipListTest {

    @Test
    public void testGet() {
        SkipList<Integer> list = new SkipList<Integer>();
        for (int i = 1; i < 40; i++) {
            list.add(new SkipNode<>(i, 666));
        }
        list.printList();
        list.delete(4);
        list.delete(8);

        System.out.println();

        list.printList();
    }

}
