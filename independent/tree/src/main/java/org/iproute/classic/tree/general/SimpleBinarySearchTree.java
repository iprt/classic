package org.iproute.classic.tree.general;

import org.iproute.classic.tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;

/**
 * 二分搜索树
 *
 * @author winterfell
 * @since 2022/6/30
 */
public class SimpleBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {

    private BSTNode<K, V> root;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(K k, V v) {
        this.root = add(root, k, v);
    }

    private BSTNode<K, V> add(BSTNode<K, V> node, K k, V v) {
        if (node == null) {
            this.size++;
            return new BSTNode<>(k, v);
        }

        K curK = node.k;
        if (k.equals(curK)) {
            node.v = v;
            return node;
        } else if (k.compareTo(curK) < 0) {
            node.left = add(node.left, k, v);
        } else {
            node.right = add(node.right, k, v);
        }
        return node;
    }

    @Override
    public V get(K k) {
        if (root == null) {
            return null;
        }
        BSTNode<K, V> find = get(root, k);
        return find == null ? null : find.v;
    }


    private BSTNode<K, V> get(BSTNode<K, V> node, K k) {
        K curK = node.k;

        if (k.equals(curK)) {
            return node;
        }

        // 寻找左节点
        if (k.compareTo(curK) < 0) {
            BSTNode<K, V> left = node.left;
            if (left == null) {
                return null;
            }
            return get(left, k);
        }

        // 寻找右节点
        BSTNode<K, V> right = node.right;
        return right == null ? null : get(right, k);
    }


    /**
     * 二叉树删除节点的思路
     * <p>
     * 思路： 找到右子树最小的点 或者 左子树最大的点 替换找到的点
     *
     * @param k the k
     */
    @Override
    public boolean delete(K k) {

        /*
                5
              /   \
            1       3
                   /  \
                  [2]  4

        ----- ----- ----- -----

               [2]
              /   \
            1       3
                      \
                       4
         */


        BSTNode<K, V> dNode = get(root, k);
        if (dNode == null) {
            return false;
        }

        root = delete(root, k);
        this.size--;

        return true;
    }


    /**
     * Delete bst node.
     *
     * @param node the node 起始节点
     * @param k    the k 值
     * @return 返回删除节点后新的二分搜索树的根
     */
    BSTNode<K, V> delete(BSTNode<K, V> node, K k) {
        if (node == null) {
            return null;
        }

        int eq = k.compareTo(node.k);
        if (eq == 0) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            BSTNode<K, V> leftSub = node.left;
            BSTNode<K, V> rightSub = node.right;

            // 找到右子树最小的节点
            BSTNode<K, V> rightSubMin = min(node.right);

            node = rightSubMin;

            node.left = leftSub;
            node.right = rightSub;

            // 删除右子树的最小节点
            this.delete(node.right, rightSubMin.k);

            return rightSubMin;

        } else if (eq < 0) {
            node.left = delete(node.left, k);
            return node;
        } else {
            node.right = delete(node.right, k);
            return node;
        }
    }


    // 广度遍历
    @Override
    public void bfs(BiConsumer<K, V> action) {
        if (this.root == null) {
            return;
        }
        Queue<BSTNode<K, V>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            action.accept(node.k, node.v);

            BSTNode<K, V> left = node.left;
            BSTNode<K, V> right = node.right;

            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }

    @Override
    public K min() {
        BSTNode<K, V> min = min(root);
        return min == null ? null : min.k;
    }

    public BSTNode<K, V> min(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }


    @Override
    public K max() {
        BSTNode<K, V> max = max(root);
        return max == null ? null : max.k;
    }

    public BSTNode<K, V> max(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }


    @Override
    public void preOrder(BiConsumer<K, V> action) {
        // 默认的dfs
        this.preOrder(root, action);
    }


    private void preOrder(BSTNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }
        action.accept(node.k, node.v);

        preOrder(node.left, action);
        preOrder(node.right, action);
    }


    @Override
    public void inOrder(BiConsumer<K, V> action) {
        this.inOrder(root, action);
    }

    private void inOrder(BSTNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }
        inOrder(node.left, action);

        action.accept(node.k, node.v);

        inOrder(node.right, action);
    }


    @Override
    public void postOrder(BiConsumer<K, V> action) {
        this.postOrder(root, action);
    }

    private void postOrder(BSTNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }
        postOrder(node.left, action);
        postOrder(node.right, action);

        action.accept(node.k, node.v);
    }

}
