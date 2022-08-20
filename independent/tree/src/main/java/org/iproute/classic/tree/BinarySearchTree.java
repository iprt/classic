package org.iproute.classic.tree;

import java.util.function.BiConsumer;

/**
 * 二分搜索树
 *
 * @author zhuzhenjie
 * @since 2022/8/20
 */
public interface BinarySearchTree<K extends Comparable<K>, V> extends BinaryTree<K, V> {

    /**
     * 获取最小节点
     *
     * @return the k
     */
    K min();


    /**
     * 获取最大节点
     *
     * @return the k
     */
    K max();

    @Override
    default void dfs(BiConsumer<K, V> action) {
        this.preOrder(action);
    }
}
