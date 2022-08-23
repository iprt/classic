package org.iproute.classic.tree.avl;

import org.iproute.classic.tree.BinarySearchTree;

import java.util.function.BiConsumer;

/**
 * AvlTree
 *
 * @author winterfell
 * @since 2022/6/30
 */
public class AvlTree<K extends Comparable<K>,V> implements BinarySearchTree<K,V> {

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(K k, V v) {

    }

    // 插入的时候需要旋转


    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public boolean delete(K k) {
        return false;
    }

    @Override
    public void bfs(BiConsumer<K, V> action) {

    }

    @Override
    public void preOrder(BiConsumer<K, V> action) {

    }

    @Override
    public void inOrder(BiConsumer<K, V> action) {

    }

    @Override
    public void postOrder(BiConsumer<K, V> action) {

    }
}
