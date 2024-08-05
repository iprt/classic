package org.iproute.classic.tree.test;

import org.iproute.classic.tree.BinarySearchTree;
import org.iproute.classic.tree.general.SimpleBST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

/**
 * BinarySearTreeTest
 *
 * @author zhuzhenjie
 * @since 2022/8/20
 */
public class SimpleBSTTest {

    @Test
    public void testCreate() {
        BinarySearchTree<Integer, String> bst = new SimpleBST<>();
        System.out.println(bst);
    }


    @Test
    public void testAdd() {
        BinarySearchTree<Integer, String> bst = new SimpleBST<>();

        bst.add(2, "222");
        bst.add(1, "111");
        bst.add(3, "333");


        bst.bfs((k, v) -> System.out.printf("k = %d, v = %s%n", k, v));
        System.out.println("size = " + bst.size());

        System.out.println();


        bst.add(1, "AAA");
        // 深度遍历
        bst.dfs((k, v) -> System.out.printf("k = %d, v = %s%n", k, v));
        System.out.println("size = " + bst.size());

        System.out.println();

        bst.add(4, "444");
        bst.bfs((k, v) -> System.out.printf("k = %d, v = %s%n", k, v));
        System.out.println("size = " + bst.size());

    }


    @Test
    public void testGet() {
        BinarySearchTree<Integer, String> bst = new SimpleBST<>();

        bst.add(2, "222");
        bst.add(1, "111");
        bst.add(3, "333");
        bst.add(4, "444");

        String s = bst.get(4);
        Assertions.assertEquals("444", s);


        bst.add(4, "DDD");
        s = bst.get(4);
        Assertions.assertEquals("DDD", s);
    }


    @Test
    public void testMinAndMax() {
        BinarySearchTree<Integer, String> bst = new SimpleBST<>();

        bst.add(2, "222");
        bst.add(1, "111");
        bst.add(3, "333");
        bst.add(4, "444");


        Integer min = bst.min();
        Integer max = bst.max();

        Assertions.assertArrayEquals(new int[]{1}, new int[]{min});

        Assertions.assertArrayEquals(new int[]{4}, new int[]{max});
    }


    @Test
    public void testOrder() {
        BinarySearchTree<Integer, String> bst = new SimpleBST<>();

        bst.add(2, "222");
        bst.add(1, "111");
        bst.add(3, "333");
        bst.add(4, "444");

        BiConsumer<Integer, String> action = (k, v) -> System.out.printf("k = %d, v = %s%n", k, v);

        System.out.println("----------");
        bst.preOrder(action);

        System.out.println("----------");
        bst.inOrder(action);

        System.out.println("----------");
        bst.postOrder(action);
    }

    @Test
    public void testDelete() {

        BinarySearchTree<Integer, String> bst = new SimpleBST<>();
        bst.add(2, "2");
        bst.add(1, "1");
        bst.add(4, "4");
        bst.add(3, "3");
        bst.add(5, "5");

        BiConsumer<Integer, String> action = (k, v) -> System.out.printf("k = %d, v = %s%n", k, v);

        bst.bfs(action);

        bst.delete(2);
        System.out.println();
        bst.bfs(action);
    }

}
