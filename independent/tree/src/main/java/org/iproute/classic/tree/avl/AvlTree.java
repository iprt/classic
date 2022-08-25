package org.iproute.classic.tree.avl;

import org.iproute.classic.tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
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
        AvlNode<K, V> min = min(root);
        return min == null ? null : min.k;
    }

    private AvlNode<K, V> min(AvlNode<K, V> node) {
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
        AvlNode<K, V> max = max(root);
        return max == null ? null : max.k;
    }

    private AvlNode<K, V> max(AvlNode<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(K k, V v) {
        this.root = add(this.root, k, v);
    }

    /**
     * Gets height.
     *
     * @param node the node
     * @return the height
     */
    private int getHeight(AvlNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 左子树和右子树的高度差
     *
     * @return the balance factor
     */
    private int getBalanceFactor(AvlNode<K, V> node) {
        return getHeight(node.left) - getHeight(node.right);
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
        int balanceFactor = this.getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            // 新增节点后，当前节点失去平衡
            System.out.println("unbalanced : balanceFactor = " + balanceFactor);

        }

        // 平衡的时机，是在平衡被打破后，回溯的时候回发现节点的平衡因子大于1 或者 小于 -1

        // ●  ⚪  ▲  △

        // 旋转思路: 砍掉子节点 -> 接到当前节点上 -> 降级当前节点

        // LL
        if (balanceFactor == 2 && getBalanceFactor(node.left) >= 0) {
            /*

                z
               /
              y
             /
            x

             */
            return rightRotate(node);
        }

        // RR
        if (balanceFactor == -2 && getBalanceFactor(node.right) <= 0) {
            /*

            z
             \
              y
               \
                x

             */
            return leftRotate(node);
        }

        // LR
        // z -> balanceFactor == 2
        // y -> getBalanceFactor(node.left) < 0
        if (balanceFactor == 2 && getBalanceFactor(node.left) < 0) {
            /*
                z
               /
              y
               \
                x


            ↓ ↓ ↓ ↓ ↓ ↓
                z
               / \
              y  t4
             / \
            t1  x
               / \
              t2 t3


            对 y 节点做一次 左旋转 砍子 接己 降己
                  z
                 / \
                x  t4
               / \
              y  t3
             / \
            t1 t2


            对 z 节点做一次 右旋转 砍子 接己 降己
                  x
                /   \
               y     z
             /  \   /  \
            t1  t2 t3  t4

             */

            node.left = leftRotate(node.left);
            return rightRotate(node.left);
        }

        // RL
        if (balanceFactor == -2 && getBalanceFactor(node.right) > 0) {
            /*

              z
               \
                y
               /
              x

            ↓ ↓ ↓ ↓ ↓ ↓

               z
              / \
             t1  y
                / \
               x  t4
              / \
             t2 t3

            对 y 节点做一次右旋转

               z
              / \
             t1  x
                / \
               t2  y
                  / \
                 t3 t4

            对 z 节点做一次左旋转

                  x
                /  \
               z     y
              / \   / \
             t1 t2 t3  t4

             */

            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 右旋转
     * <p>
     * node 的 left 的 left 新增了元素导致不平衡
     *
     * @param z the z
     * @return the avl node
     */
    private AvlNode<K, V> rightRotate(AvlNode<K, V> z) {
       /*
       平衡的时机：发现当前节点的左子树的高度 - 右子树的高度 > 1了
         - balanceFactor > 1
         - y 的 左子树 比 右子树 高, y.balanceFactor > 1

            ●[z] (平衡因子为2，左子树更高)
           /
          ⚪[y]
         /
        ⚪[x]

        ↓ ↓ ↓  通用性结构 ↓ ↓ ↓

                  ●[z] (平衡因子为2，左子树更高)
                /   \
              ⚪[y]   △[T4]
             /    \
           ⚪[x]  ▲[T3]
          /    \
        △[T1]  △[T2]

        存在 T1 < x < T2 < y < T3 < z < T4

        如何判断通用性结构
          - 当前节点的左子树的高度大于右子树，balanceFactor > 1
          - 且 左子树的左子树的高度 > 左子树的右子树的高度
            - node.left.balanceFactor >= 0

        ↓ ↓ ↓  旋转 ↓ ↓ ↓

        T3 挂到 z 节点的 左节点
        当前节点挂到 当前节点的右节点


                  ⚪[y]
               /       \
           ⚪[x]         ●[z]
          /    \        /    \
        △[T1]  △[T2]  ▲[T3]  △[T4]

         */

        AvlNode<K, V> y = z.left;
        AvlNode<K, V> t3 = y.right;

        y.right = z;
        z.left = t3;


        // 高度会发生变化的只有 y 和 z
        // 先计算 更新z的高度 ，再计算更新y的高度
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }


    /**
     * 左旋转
     * <p>
     * node 的 right 的 right 新增了元素导致不平衡
     *
     * @param z the node
     * @return the avl node
     */
    private AvlNode<K, V> leftRotate(AvlNode<K, V> z) {
        /*
        旋转的时机
        节点 z 在回溯的时候发现平衡因子小于 -1 了
          - 说明当前节点右子树的高度比左子树的高度大2
          - 且
          - 节点 y 的 右子树 比左子树高 ， 即 y.balanceFactor <= 0

        z
         \
          y
           \
            x

        ↓ ↓ ↓  通用性结构 ↓ ↓ ↓

            z
          /   \
         t1    y
             /   \
            t2    x
                /   \
               t3   t4

        存在 t1 < z < t2 < y < t3 < x < t4

        左旋转
        - t2 放到 z 的右子树
        - z  放到 y 的左子树

               y
             /   \
            z     x
           / \   /  \
          t1 t2  t3  t4
         */
        AvlNode<K, V> y = z.right;
        AvlNode<K, V> t2 = y.left;

        y.left = z;
        z.right = t2;

        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }


    // 检测树是否是平衡的，本质上就是检测每个节点左子树的高度或者右子树的高度是否大于1
    @Override
    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    public boolean isBalanced(AvlNode<K, V> node) {
        if (node == null) {
            return true;
        }

        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        // 左右子树都需要是平衡的
        return isBalanced(node.left) && isBalanced(node.right);
    }


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
        Queue<AvlNode<K, V>> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            AvlNode<K, V> poll = nodes.poll();

            if (action != null) {
                action.accept(poll.k, poll.v);
            }

            AvlNode<K, V> left = poll.left;
            if (left != null) {
                nodes.add(left);
            }

            AvlNode<K, V> right = poll.right;
            if (right != null) {
                nodes.add(right);
            }
        }
    }

    @Override
    public void preOrder(BiConsumer<K, V> action) {
        preOrder(root, action);
    }

    private void preOrder(AvlNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }

        if (action != null) {
            action.accept(node.k, node.v);
        }

        preOrder(node.left, action);
        preOrder(node.right, action);

    }


    @Override
    public void inOrder(BiConsumer<K, V> action) {
        this.inOrder(root, action);
    }

    private void inOrder(AvlNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }

        inOrder(node.left, action);

        if (action != null) {
            action.accept(node.k, node.v);
        }

        inOrder(node.right, action);
    }

    @Override
    public void postOrder(BiConsumer<K, V> action) {
        this.postOrder(root, action);
    }

    private void postOrder(AvlNode<K, V> node, BiConsumer<K, V> action) {
        if (node == null) {
            return;
        }
        postOrder(node.left, action);
        postOrder(node.right, action);

        if (action != null) {
            action.accept(node.k, node.v);
        }
    }

}
