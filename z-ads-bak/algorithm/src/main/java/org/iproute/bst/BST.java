package org.iproute.bst;

import java.util.Map;
import java.util.TreeMap;

public class BST<K extends Comparable<K>, V> {

    private int count;

    private Node<K, V> root;

    public BST() {
        this.count = 0;
        this.root = null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (null == node) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result > 0) {
            node.right = delete(node.right, key);
        } else if (result < 0) {
            node.left = delete(node.left, key);
        } else if (node.left != null && node.right != null) {
            // 使用右子树的最小节点替代当前节点
            Node<K, V> tmp = findMin(node.right);
            // 节点更新
            node.key = tmp.key;
            node.value = tmp.value;
            node.right = delete(node.right, tmp.key);
        } else {
            node = (node.left != null) ? node.left : node.right;
            this.count--;
        }
        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }
    }

    // 寻找最大最小
    private Node<K, V> findMax() {
        return findMax(root);
    }

    private Node<K, V> findMax(Node<K, V> node) {
        if (root == null) {
            return null;
        }
        return (node.right == null) ? node : findMax(node.right);
    }

    private Node<K, V> findMin() {
        return findMin(root);
    }

    private Node<K, V> findMin(Node<K, V> node) {
        if (root == null) {
            return null;
        }
        return (node.left == null) ? node : findMax(node.left);
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            count++;
            return new Node<>(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V find(K key) {
        Node<K, V> res = find(root, key);
        return res == null ? null : res.value;
    }

    private Node<K, V> find(Node<K, V> node, K k) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(k) == 0) {
            return node;
        } else if (k.compareTo(node.key) < 0) {
            return find(node.left, k);
        } else {
            return find(node.right, k);
        }
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<K, V> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("key:" + node.key + " value:" + node.value);
            inOrder(node.right);
        }
    }

    static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        test();
    }

    static void test() {
        String word = "this this is a dog that is a cat and there is a bird bird";
        BST<String, Integer> bst = new BST<>();
        String[] words = word.split(" ");
        for (String t : words) {
            if (bst.contains(t)) {
                bst.insert(t, bst.find(t) + 1);
            } else {
                bst.insert(t, 1);
            }
        }
        bst.inOrder();
        Map<String, Integer> m = new TreeMap<>();
        for (String t : words) {
            if (m.containsKey(t)) {
                m.put(t, m.get(t) + 1);
            } else {
                m.put(t, 1);
            }
        }
        m.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
    }
}
