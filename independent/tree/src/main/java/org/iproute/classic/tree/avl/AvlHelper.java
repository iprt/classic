package org.iproute.classic.tree.avl;

/**
 * Avl 节点的旋转
 *
 * @author zhuzhenjie
 * @since 2022/8/25
 */
public class AvlHelper {

    /**
     * Gets height.
     *
     * @param <K>  the type parameter
     * @param <V>  the type parameter
     * @param node the node
     * @return the height
     */
    static <K extends Comparable<K>, V> int getHeight(AvlNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }


    /**
     * Cal height.
     *
     * @param <K>  the type parameter
     * @param <V>  the type parameter
     * @param node the node
     */
    static <K extends Comparable<K>, V> void updateHeight(AvlNode<K, V> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }


    /**
     * Get balance factor.
     *
     * @param <K>  the type parameter
     * @param <V>  the type parameter
     * @param node the node
     * @return the balance factor
     */
    static <K extends Comparable<K>, V> int getBalanceFactor(AvlNode<K, V> node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Right rotate.
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     * @param x   the x
     */
    static <K extends Comparable<K>, V> AvlNode<K, V> rightRotate(AvlNode<K, V> x) {
        /*
             x
            /\
           y  t4
          /\
         z  t3
        / \
        t1 t2

             y
          /    \
         z      x
        / \    / \
        t1 t2 t3 t4
         */

        AvlNode<K, V> y = x.left;
        AvlNode<K, V> t3 = y.right;

        x.left = t3;
        y.right = x;

        updateHeight(x);
        updateHeight(y);

        return y;
    }


    /**
     * Left rotate avl node.
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     * @param x   the x
     * @return the avl node
     */
    static <K extends Comparable<K>, V> AvlNode<K, V> leftRotate(AvlNode<K, V> x) {
        /*
          x
         / \
        t1  y
           / \
         t2   z
             / \
            t3 t4

             y
           /   \
          x     z
         / \   / \
        t1 t2 t3 t4
         */


        AvlNode<K, V> y = x.right;
        AvlNode<K, V> t2 = y.left;

        x.right = t2;
        y.left = x;

        updateHeight(x);
        updateHeight(y);

        return y;
    }


    /**
     * 节点平衡操作
     *
     * @param <K>  the type parameter
     * @param <V>  the type parameter
     * @param node the node
     * @return the avl node
     */
    static <K extends Comparable<K>, V> AvlNode<K, V> balance(AvlNode<K, V> node) {
        int balanceFactor = getBalanceFactor(node);

        // LL
        if (balanceFactor == 2 &&
                (getBalanceFactor(node.left) == 1 || getBalanceFactor(node.left) == 0)
        ) {
            return rightRotate(node);
        }

        // RR
        if (balanceFactor == -2 &&
                (getBalanceFactor(node.right) == -1 || getBalanceFactor(node.right) == 0)
        ) {
            return leftRotate(node);
        }

        // LR
        if (balanceFactor == 2 && getBalanceFactor(node.left) == -1) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor == -2 && getBalanceFactor(node.right) == 1) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        throw new RuntimeException("Error implementation of AVL Tree.");

    }

}