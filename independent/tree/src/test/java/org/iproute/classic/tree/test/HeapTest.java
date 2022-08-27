package org.iproute.classic.tree.test;

import org.iproute.classic.tree.Heap;
import org.iproute.classic.tree.heap.FixLengthMaxHeap;
import org.junit.Test;

/**
 * HeapTest
 *
 * @author zhuzhenjie
 * @since 2022/8/27
 */
public class HeapTest {

    @Test
    public void testAdd() {
        int max = 10;
        Heap<Integer> heap = new FixLengthMaxHeap<>(max);

        heap.setDebug(true);

        for (int i = 1; i <= max; i++) {
            heap.add(i);
        }

        System.out.println("heap's size is " + heap.size());
    }


    @Test
    public void testExtra() {
        int max = 1<<6;
        Heap<Integer> heap = new FixLengthMaxHeap<>(max);
        heap.setDebug(false);

        for (int i = 1; i <= max; i++) {
            heap.add(i);
        }
        System.out.println("heap's size is " + heap.size());

        for (int i = 0; i < max; i++) {
            Integer v = heap.extractMax();
            System.out.printf(v + "\t");
        }
        System.out.println();
        System.out.println("heap's size is " + heap.size());
    }

}
