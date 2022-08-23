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


    /**
     * 节点的高度， 怎么理解这个高度
     * <p>
     * 这个高度是节点到最下一层的叶子节点的高度
     */
    int height;

    AvlNode<K, V> left;
    AvlNode<K, V> right;

    public AvlNode(K k, V v) {
        this.k = k;
        this.v = v;

        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
