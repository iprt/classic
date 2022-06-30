package org.iproute.avltreemt;

import java.util.Map;
import java.util.TreeMap;

public class BST<K extends Comparable, V> {

    private int count;

    private Node root;

    public BST() {
        this.count = 0;
        this.root = null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
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
            Node tmp = findMin(node.right);
            // 更新节点属性
            node.key = tmp.key;
            node.value = tmp.value;
            node.right = delete(node.right, tmp.key);
        } else {
            // 最终会到这里
            node = (node.left != null) ? node.left : node.right;
            this.count--;
        }
        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
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
    private Node findMax() {
        return findMax(root);
    }

    private Node findMax(Node node) {
        if (root == null) {
            return null;
        }
        return (node.right == null) ? node : findMax(node.right);
    }

    private Node findMin() {
        return findMin(root);
    }

    private Node findMin(Node node) {
        if (root == null) {
            return null;
        }
        return (node.left == null) ? node : findMax(node.left);
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) {
            count++;
            return new Node(key, value);
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
        Node res = find(root, key);
        return res == null ? null : res.value;
    }

    private Node find(Node node, K k) {
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

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("key:" + node.key + " value:" + node.value);
            inOrder(node.right);
        }
    }

    class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
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
        System.out.println("size " + bst.size());
        bst.delete("is");
        System.out.println("size " + bst.size());
        bst.delete("xxxxx");
        System.out.println("size " + bst.size());
        bst.inOrder();


        Map<String, Integer> m = new TreeMap<>();
        for (String t : words) {
            if (m.containsKey(t)) {
                m.put(t, m.get(t) + 1);
            } else {
                m.put(t, 1);
            }
        }
        System.out.println("***********************************************");
        m.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
    }
}
