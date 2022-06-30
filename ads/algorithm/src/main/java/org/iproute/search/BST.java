package org.iproute.search;

import org.iproute.sort.helper.SortTestHelper;

import java.util.LinkedList;

/**
 * Created by winterfell on 2017/10/9.
 * <p>
 * 二叉搜索树本身的数据结构是一种递归的性质
 * 递归！！！ 递归！！！ 递归！！！
 */
public class BST<Key extends Comparable, Value> {

    // 数的节点为私有类，外界不需要了解二分搜索树节点的具体实现
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }


    private Node root; // 根节点
    private int count; // 树种节点的个数

    // 构造函数，默认构造一个空的二分搜索树


    public BST() {
        this.root = null;
        this.count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二叉搜索树是否为空
    public boolean idEmpty() {
        return count == 0;
    }

    // 插入key value
    public void insert(Key key, Value value) {
        // 从根节点开始插入
        root = insert(root, key, value);
    }

    // 根据key查找value
    public Value search(Key key) {
        return search(root, key);
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    // 二分搜索树的层序遍历
    // 使用一个队列
    public void levelOrder() {
        // 使用LinkedList来作为我们的队列
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.remove();
            // operation
            System.out.println(node.key);

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }


    // 寻找最小值得key
    public Key minimum() {
        assert count != 0;

        Node minNode = minimum(root);

        return minNode.key;
    }

    // 寻找最大值得key
    public Key maximum() {
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }

    public void removeMin() {
        if (root != null)
            removeMin(root);
    }

    public void removeMax() {
        if (root != null) {
            removeMax(root);
        }
    }

    public void remove(Key key) {
        root = remove(root, key);
    }


    //***************************************************
    //************  二分搜索树的辅助函数  *****************
    //***************************************************

    // 删除辅助函数
    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        // 待完善
        return null;
    }

    // 插入辅助函数
    // 二叉搜索树插入的辅助函数
    // 向以node为根的二叉搜索树中，插入节点(key,value)
    // 返回插入新节点后的二叉搜索树的根
    private Node insert(Node node, Key key, Value value) {

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
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }
        return node;
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


    // 前序遍历
    private void preOrder(Node node) {
        if (null != node) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 中序遍历
    private void inOrder(Node node) {
        if (null != node) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }


    private void postOrder(Node node) {
        if (null != node) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
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


    public static void main(String[] args) {

        BST<Integer, String> bst = new BST<>();

        for (int i = 0; i < 50; i++) {
            bst.insert(new Integer(i + 1), (i + 1) + "");
        }

        System.out.println(bst.search(new Integer(20)));

        System.out.println(bst.search(new Integer(101)));

        BST<Integer, String> bst2 = new BST<>();

        Integer[] arr = SortTestHelper.generateRandomArray(50, 0, 100);
        for (int i = 0; i < arr.length; i++) {
            bst2.insert(new Integer(arr[i]), new String("hello"));
        }

        bst2.inOrder();

    }

}
