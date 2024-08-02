package org.iproute.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BinarySearchTree
 *
 * @author tech@intellij.io
 * @since 2021/11/15
 */
public class BinarySearchTree {

    /**
     * root node 的本质是给递归一个起点，本身二分搜索树的任意一个点拆分出去都是二分搜索树
     * <p>
     * 递归的起点，整个还是很重要的！！！
     */
    private Node root;

    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    // 二分搜索树简单的性质
    Node getMax() {
        // 迭代的写法
        Node tmp = root;
        while (tmp != null && tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    Node getMax2() {
        return getMax(root);
    }

    private Node getMax(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    Node getMin2() {
        return getMin(root);
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    Node getMin() {
        // 迭代的写法
        Node tmp = root;
        while (tmp != null && tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    // 判断二叉树是否存在key
    public boolean exists(int key) {
        return get(root, key) != null;
    }

    /**
     * 递归的方式加入元素
     *
     * @param key   the key
     * @param value the value
     */
    public void put(int key, String value) {
        if (value == null) {
            throw new IllegalArgumentException("value == null");
        }
        this.root = putByRecursion(root, key, value);
    }

    /**
     * 遍历的方式加入元素
     *
     * @param key   the key
     * @param value the value
     */
    public void put2(int key, String value) {
        if (value == null) {
            throw new IllegalArgumentException("value == null");
        }
        if (root == null) {
            this.root = new Node(key, value);
            return;
        }
        putByIteration(root, key, value);
    }

    // 使用迭代的写法
    public void putByIteration(Node node, int key, String value) {
        while (node != null) {
            if (key < node.key) {
                Node left = node.left;
                if (left == null) {
                    node.left = new Node(key, value);
                    this.size++;
                    break;
                }
                node = left;
                continue;
            }
            if (key > node.key) {
                Node right = node.right;
                if (right == null) {
                    node.right = new Node(key, value);
                    this.size++;
                    break;
                }
                node = right;
                continue;
            }
            node.value = value;
            break;
        }

    }

    // 使用递归的写法
    private Node putByRecursion(Node node, int key, String value) {
        // 递归写法
        // 这种写法可能存在一定问题，put方法使用的时候 root都会重新赋值，不确定对jvm有什么影响
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (key < node.key) {
            node.left = putByRecursion(node.left, key, value);
            return node;
        }

        if (key > node.key) {
            node.right = putByRecursion(node.right, key, value);
            return node;
        }

        node.value = value;
        return node;
    }

    // 错误的递归示例，值传递和引用传递的问题
    private void put_error(Node node, int key, String value) {
        // 这里面遇到一个坑
        if (node == null) {
            node = new Node(key, value);
            return;
        }

        if (key == node.key) {
            // value 更新
            node.value = value;
            return;
        }

        if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key, value);
                return;
            }
            put_error(node.left, key, value);
        }

        if (node.right == null) {
            node.right = new Node(key, value);
            return;
        }
        put_error(node.right, key, value);

    }

    /**
     * Get
     *
     * @param key the key
     * @return the string
     */
    public String get(int key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.key) {
            return node;
        }
        if (key < node.key) {
            Node left = node.left;
            return get(left, key);
        }
        // if (key > node.key)
        Node right = node.right;
        return get(right, key);
    }

    // 前序遍历 先遍历父节点，再遍历左节点，再遍历右节点  父左右
    public void traverseFront() {
        traverseFront(root);
        System.out.println();
    }

    private void traverseFront(Node node) {
        if (node == null) {
            return;
        }
        // 先序遍历什么都管，拿到父节点就输出
        System.out.print(node.key + "  ");
        traverseFront(node.left);
        traverseFront(node.right);
    }

    // 中序遍历 先遍历左节点，再遍历父节点，最后遍历右节点 左父右
    public void traverseCenter() {
        traverseCenter(root);
        System.out.println();
    }

    private void traverseCenter(Node node) {
        if (node == null) {
            return;
        }
        // 当一直递归到没有左节点之后
        traverseCenter(node.left);
        System.out.print(node.key + "  ");
        traverseCenter(node.right);
    }


    // 后序遍历 先遍历左节点，再遍历右节点，最后遍历父节点 左右父
    public void traverseBack() {
        traverseBack(root);
        System.out.println();
    }

    private void traverseBack(Node node) {
        if (node == null) {
            return;
        }
        traverseBack(node.left);
        traverseBack(node.right);
        System.out.print(node.key + "  ");
    }

    // 层序遍历
    public void levelTraverse() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            System.out.print(poll.key + "  ");

            if (poll.left != null) {
                nodes.offer(poll.left);
            }

            if (poll.right != null) {
                nodes.offer(poll.right);
            }
        }
        System.out.println();
    }

    public void reverse() {
        this.reverse(root);
    }

    // 二叉树反转
    void reverse(Node node) {
        if (node.left == null && node.right == null) {
            return;
        }
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        reverse(node.left);
        reverse(node.right);
    }


    // 二叉树节点删除
    public void delete(int key) {
        // 根据二叉树的性质，找删除的节点的左子树最大的元素或者右子树的最小的元素
        if (!exists(key)) {
            return;
        }
        root = delete(root, key);
    }

    // 核心： 返回删除了的节点的新树
    // 参数的node代表要删除的节点
    // 返回的node代表要基于节点后删除的子树的返回
    private Node delete(Node node, int key) {
        if (key < node.key) {
            node.left = delete(node.left, key);
            return node;
        } else if (key > node.key) {
            node.right = delete(node.right, key);
            return node;
        } else {
            // 找到要删除的节点 , 分情况讨论
            // 1. 左右节点都为空
            if (node.left == null && node.right == null) {
                this.size--;
                return null;
            }

            // 2.1. 左子树为空
            if (node.left == null) {
                // 没有左子树 当前node替换掉
                this.size--;
                return node.right;
            }

            // 2.2. 右子树为空
            if (node.right == null) {
                this.size--;
                return node.left;
            }

            // 3.3. 最后一种情况，左右子树都不为空
            // 寻找左子树最大的值
            Node leftTreeMaxNode = getMax(node.left);
            // 赋值该改变
            node.key = leftTreeMaxNode.key;
            node.value = leftTreeMaxNode.value;

            // 删除左子树最大的那个值,以当前节点的左子树为根
            node.left = this.delete(node.left, leftTreeMaxNode.key);
            return node;
        }
    }

    private static class Node {
        Node left;
        Node right;
        int key;
        String value;

        Node(Node left, Node right, int key, String value) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

        Node(int key, String value) {
            this(null, null, key, value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        // 4 2 1 3 6 5 7
        bst.put(4, "4");
        bst.put2(2, "2");
        bst.put(1, "1");
        bst.put2(3, "3");
        bst.put(6, "6");
        bst.put2(5, "5");
        bst.put(7, "7");

        System.out.println("--- 基础测试 ——-");

        System.out.println(bst.getSize());

        System.out.println("max node :" + bst.getMax());
        System.out.println("min node :" + bst.getMin());

        System.out.println("max node 2 :" + bst.getMax2());
        System.out.println("min node 2:" + bst.getMin2());

        System.out.println("get 6 : " + bst.get(6));
        System.out.println("get 1 : " + bst.get(1));
        System.out.println("get 8 : " + bst.get(8));

        System.out.println("--- 前序遍历 ——-");
        bst.traverseFront();

        System.out.println("--- 中序遍历 ——-");
        bst.traverseCenter();

        System.out.println("--- 后序遍历 ——-");
        bst.traverseBack();

        System.out.println("--- 层序遍历 ---");
        bst.levelTraverse();


        System.out.println("--- 二叉树反转 ——--");
        bst.traverseCenter();
        bst.reverse();
        System.out.println("--- 二叉树反转过后 ——--");
        bst.traverseCenter();
        bst.reverse();


        System.out.println("--- 二叉树删除 ---");
        bst.delete(6);
        bst.traverseCenter();

        // 二叉树删除
        /*
        for (int i = 1; i <= 7; i++) {
            bst.delete(i);
        }
        *
        */

    }
}
