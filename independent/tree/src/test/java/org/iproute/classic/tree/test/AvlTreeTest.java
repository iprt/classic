package org.iproute.classic.tree.test;

import org.iproute.classic.tree.avl.AvlTree;
import org.junit.Test;

/**
 * AvlTreeTest
 *
 * @author zhuzhenjie
 * @since 2022/8/24
 */
public class AvlTreeTest {

    @Test
    public void testAdd() {

        AvlTree<Integer, String> tree = new AvlTree<>();

        for (int i = 1; i <= 10; i++) {
            tree.add(i, String.valueOf(i));
        }

        tree.bfs(null);

        boolean isBST = tree.isBST();
        System.out.println("is BST = " + isBST);

        boolean balanced = tree.isBalanced();
        System.out.println("is balanced = " + balanced);

    }

}
