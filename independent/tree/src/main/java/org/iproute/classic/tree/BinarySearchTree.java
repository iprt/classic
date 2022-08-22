package org.iproute.classic.tree;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 是否是真正的二分搜索树
     *
     * @return the boolean
     */
    default boolean isRealBST() {
        if (isEmpty()) {
            return true;
        }
        List<K> kList = new ArrayList<>(size());
        inOrder((k, v) -> kList.add(k));

        for (int i = 0; i < kList.size() - 1; i++) {
            K before = kList.get(i);
            K after = kList.get(i + 1);
            if (before.compareTo(after) > 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    default boolean contains(K k) {
        return this.get(k) != null;
    }

    @Override
    default void dfs(BiConsumer<K, V> action) {
        this.preOrder(action);
    }
}
