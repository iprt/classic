package org.iproute.classic.tree.avl;

import org.iproute.classic.tree.BinarySearchTree;

import java.util.function.BiConsumer;

/**
 * AvlTree
 *
 * @author winterfell
 * @since 2022/6/30
 */
public class AvlTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {

    private AvlNode<K, V> root;

    private int size;

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
        return this.size;
    }

    @Override
    public void add(K k, V v) {
        this.root = add(this.root, k, v);
    }

    private int getHeight(AvlNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private AvlNode<K, V> add(AvlNode<K, V> node, K k, V v) {
        if (node == null) {
            this.size++;
            return new AvlNode<>(k, v);
        }

        int cp = k.compareTo(node.k);

        if (cp == 0) {
            node.v = v;
            // avl tree 在
            return node;
        }

        if (cp < 0) {
            node.left = add(node.left, k, v);
        } else {
            node.right = add(node.right, k, v);
        }
        // 加完节点后，max{left.height,right.height} + 1
        // 更新当前节点的高度
        // node.height = Math.max(node.left.height, node.right.height) + 1;
        // ↓ ↓ ↓
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算当前节点新增节点后的平衡因子
        // int balanceFactor = Math.abs(node.left.height - node.right.height);
        // ↓ ↓ ↓
        int balanceFactor = Math.abs(getHeight(node.left) - getHeight(node.right));
        if (balanceFactor > 1) {
            // 新增节点后，当前节点失去平衡
            System.out.println("unbalanced : balanceFactor = " + balanceFactor);
        }

        return node;
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
