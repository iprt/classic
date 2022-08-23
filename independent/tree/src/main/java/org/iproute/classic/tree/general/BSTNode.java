package org.iproute.classic.tree.general;

/**
 * BSTNode
 *
 * @author zhuzhenjie
 * @since 2022/8/20
 */
public class BSTNode<K extends Comparable<K>, V> {
    final K k;

    V v;

    BSTNode<K, V> left;

    BSTNode<K, V> right;

    public BSTNode(K k, V v) {
        this.k = k;
        this.v = v;

        this.left = null;
        this.right = null;
    }


    @Override
    public String toString() {
        return "BSTNode{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
