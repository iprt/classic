package org.iproute.avltree;

/**
 * @author zhuzhenjie
 **/
public class MyBST<K extends Comparable, V> implements Tree<K, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    private int count;

    public MyBST() {
        this.root = null;
        this.count = 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            this.count++;
            // new need added node
            return new Node(key, value);
        }
        if (node.key.compareTo(key) == 0) {
            node.value = value;
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return this.count > 0;
    }

    @Override
    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right.key);
        }
    }

    @Override
    public V getMin() {
        if (root == null) {
            return null;
        }
        return getMinNode().value;
    }

    private Node getMinNode() {
        return getMin(root);
    }

    private Node getMin(Node node) {
        if (node == null || node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    @Override
    public V getMax() {
        return getMaxNode().value;
    }

    private Node getMaxNode() {
        return getMax(root);
    }

    private Node getMax(Node node) {
        if (root == null) {
            return null;
        }
        return node.right == null ? node : getMax(node);
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(node.left, key);
        } else if (compare > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
                this.count--;
            }

            if (node.right == null) {
                node = node.left;
                this.count--;
            }
            // 找到右子树最大
            Node rightChildrenMaxNode = getMax(node.right);
            node.key = rightChildrenMaxNode.key;
            node.value = rightChildrenMaxNode.value;
            // 删除右子树最大
            node.right = remove(node.right, rightChildrenMaxNode.key);
            this.count--;
        }
        return node;
    }

    private void removeMin() {
        root = removeMin(root);
    }

    // 左子节点遍历
    private Node removeMin(Node node) {
        // 没有左节点，表明是最小值
        if (node.left == null) {
            Node rightNode = node.right;
            // 在c++里面要对node进行回收\
            // 解除引用 jdk自动回收
//            node.right = null; // 写法1
//            node = null;  // 写法2
            this.count--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public static void main(String[] args) {

        MyBST<Integer, Integer> myBST = new MyBST<>();
        myBST.add(3, 3);
        myBST.add(1, 1);
        myBST.add(2, 2);

        myBST.removeMin();

        System.out.println("...");

    }

}
