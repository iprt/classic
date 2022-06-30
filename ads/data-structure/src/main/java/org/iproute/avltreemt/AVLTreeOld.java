package org.iproute.avltreemt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winterfell
 * 基于二分搜索树的改进 原因 二分搜索树可能退化为链表
 * 1. 记录每一个节点的高度 并计算平衡因子
 * 2. 计算是否平衡
 * 3. AVL树的左旋转 和 右旋转
 * 4. 平衡维护的时机 加入节点的时候 沿着节点向上维护平衡性
 * 5. 旋转操作的核心是节点的替换 返回正确的节点
 * 6. 从AVL树里面删除节点
 */
public class AVLTreeOld<Key extends Comparable, Value> {

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        public int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root; // 根节点
    private int count; // 树种节点的个数


    public AVLTreeOld() {
        this.root = null;
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean idEmpty() {
        return count == 0;
    }

    public void add(Key key, Value value) {
        root = add(root, key, value);
    }

    public Value search(Key key) {
        return search(root, key);
    }

    public Key minimum() {
        assert count != 0;

        Node minNode = minimum(root);

        return minNode.key;
    }

    public Key maximum() {
        assert count != 0;

        Node maxNode = maximum(root);

        return maxNode.key;
    }

    /**
     * 删除最小值
     */
    public void removeMin() {
        if (root != null)
            removeMin(root);
    }

    /**
     * 删除最大值
     */
    public void removeMax() {
        if (root != null) {
            removeMax(root);
        }
    }

    // 检查函数 检查是不是一颗二分搜索树
    public boolean isBST() {
        List<Key> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 0; i < keys.size() - 1; i++) {
            if (keys.get(i).compareTo(keys.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    // 中序遍历
    private void inOrder(Node node, List<Key> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断是不是一个平衡的二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断这个节点是不是
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 继续判断左子树和右子树
        return isBalanced(node.right) && isBalanced(node.right);
    }

    /**
     * 获得一个节点的高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获得节点node的平衡因子
     */
    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return node.left.height - node.right.height;
    }

    /**
     * 向以node为根的二叉搜索树中，插入节点(key,value)
     * 返回插入新节点后的二叉搜索树的根
     */
    private Node add(Node node, Key key, Value value) {
        // 递归终点
        if (node == null) {
            count++;
            // 真tm聪明，根节点为空会被这里被new出来的
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            // 更新二叉搜索树的值
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            // 反正这里插入的时候和根节点没什么关系，已经进入了递归的里面
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }
        // 添加的树高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            // 输出不平衡的时候 平衡因子
            System.out.println("unbalanced: " + balanceFactor);
        }

        /****************** 维护平衡性 *********************/
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) { // 左节点的左节点多添加了节点
            // 右旋 更新节点
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {// 右节点的右节点添加了节点打破平衡
            // 左旋 形成新的根节点
            return leftRotate(node);
        }
        // LR   左旋变成 LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) { // 左节点的右子树比左子树要高
            node.left = leftRotate(node.left); // 转换成 LL
            return rightRotate(node);
        }
        // RL   右旋变成 RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) { // 右节点的左子树比右子树的节点高
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 右旋
    private Node rightRotate(Node y) {
        // 暂存
        Node x = y.left;
        Node T3 = x.right;
        //
        x.right = y;
        y.left = T3;

        // 更新节点高度
        // 更新x的高度要先更新y的高度 依赖关系
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 左旋转
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        // 向左旋转的过程
        x.left = y;
        y.right = T2;
        // 更新节点的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 查找是否包含节点
    private boolean contain(Node node, Key key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }

    }

    // 如果不存在返回空
    private Value search(Node node, Key key) {

        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // 一直找左边的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 一直找右边的节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }


    // 删除以node为根的二分搜索树的最小节点
    // 返回 删除节点后 的 新的 二分搜索树 的根
    // 算法的奇妙之处在于用Node作为返回值 不断递归下去
    private Node removeMin(Node node) {

        // 递归结束
        // 没有左节点，表明是最小值
        if (node.left == null) {
            // 右边节点代替现在的新的节点成为node节点的左孩子
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }
        // node.left 是为了最后找到重新指向 最小值下面的node.right
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

}
