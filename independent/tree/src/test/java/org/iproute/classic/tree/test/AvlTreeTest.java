package org.iproute.classic.tree.test;

import org.iproute.classic.tree.avl.AvlTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiConsumer;

/**
 * AvlTreeTest
 *
 * @author zhuzhenjie
 * @since 2022/8/24
 */
public class AvlTreeTest {

    private static BiConsumer<Integer, String> PRINT = (k, v) -> System.out.printf("k = %d, v = %s%n", k, v);


    @Test
    public void testAdd() {

        AvlTree<Integer, String> tree = new AvlTree<>();

        for (int i = 1; i <= 10; i++) {
            tree.add(i, String.valueOf(i));
        }

        tree.bfs(PRINT);

        boolean isBST = tree.isBST();
        System.out.println("is BST = " + isBST);

        boolean balanced = tree.isBalanced();
        System.out.println("is balanced = " + balanced);

    }

    @Test
    public void testLL() {
        // 3 , 2 , 1
        AvlTree<Integer, String> tree = new AvlTree<>();


        tree.add(3, "333");
        tree.add(2, "222");
        tree.add(1, "111");


        tree.bfs(PRINT);

        boolean isBST = tree.isBST();

        boolean balanced = tree.isBalanced();

        Assert.assertTrue(isBST);

        Assert.assertTrue(balanced);

    }


    @Test
    public void testRR() {
        // 1 , 2 , 3
        AvlTree<Integer, String> tree = new AvlTree<>();


        tree.add(3, "333");
        tree.add(2, "222");
        tree.add(1, "111");

        tree.bfs(PRINT);

        boolean isBST = tree.isBST();

        boolean balanced = tree.isBalanced();

        Assert.assertTrue(isBST);

        Assert.assertTrue(balanced);

    }

    @Test
    public void testLR() {
        // 3 , 1 , 2

        AvlTree<Integer, String> tree = new AvlTree<>();

        tree.add(3, "333");
        tree.add(1, "111");
        tree.add(2, "222");

        tree.bfs(PRINT);

        boolean isBST = tree.isBST();

        boolean balanced = tree.isBalanced();

        Assert.assertTrue(isBST);

        Assert.assertTrue(balanced);
    }

    @Test
    public void testRL() {
        // 1 ,3 ,2

        AvlTree<Integer, String> tree = new AvlTree<>();

        tree.add(1, "111");
        tree.add(3, "333");
        tree.add(2, "222");

        tree.bfs(PRINT);

        boolean isBST = tree.isBST();

        boolean balanced = tree.isBalanced();

        Assert.assertTrue(isBST);

        Assert.assertTrue(balanced);
    }


    @Test
    public void testAddAndDelete() {
        int max = 1 << 25;
        System.out.println("max value  = " + max);

        AvlTree<Integer, Integer> avl = new AvlTree<>();

        avl.setDebug(false);

        for (int i = 1; i <= max; i++) {
            avl.add(i, i);
        }

        System.out.println("length = " + avl.size());
        System.out.println("isBalanced = " + avl.isBalanced());

        Assert.assertEquals(max, avl.size());
        Assert.assertTrue(avl.isBalanced());

        for (int i = 1; i <= max; i++) {
            avl.delete(i);
        }
        System.out.println("after delete ... ");
        System.out.println("length = " + avl.size());

        Assert.assertEquals(0, avl.size());

    }


    @Test
    public void testBfs() {
        int max = 1 << 5;
        AvlTree<Integer, String> avl = new AvlTree<>();
        for (int i = 1; i <= max; i++) {
            avl.add(i, String.valueOf(i));
        }
        avl.bfs(PRINT);
    }


    @Test
    public void testDFS(){
        int max = 1 << 5;
        AvlTree<Integer, String> avl = new AvlTree<>();
        for (int i = 1; i <= max; i++) {
            avl.add(i, String.valueOf(i));
        }
        avl.bfs(PRINT);
    }

}
