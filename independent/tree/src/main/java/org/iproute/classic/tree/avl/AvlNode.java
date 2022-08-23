package org.iproute.classic.tree.avl;

/**
 * 平衡树的节点
 *
 * @author zhuzhenjie
 * @since 2022/8/23
 */
public class AvlNode<K extends Comparable<K>, V> {
    K k;
    V v;

    int height;

    AvlNode<K, V> left;
    AvlNode<K, V> right;

    public AvlNode(K k, V v, int height) {
        this.k = k;
        this.v = v;
        this.height = height;
        this.left = null;
        this.right = null;
    }

    public AvlNode(K k, V v) {
        this.k = k;
        this.v = v;

        this.height = 1;
        this.left = null;
        this.right = null;
    }

}
