package winterfell.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;

/**
 * @author zhuzhenjie
 **/
@SuppressWarnings("all")
public class BST<K extends Comparable, V> {

    private Node root;

    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public V get(K key) {
        return get(root, key);
    }

    public V remove(K key) {
        if (!contains(key)) {
            return null;
        }
        return remove(root, key).value;
    }

    public void foreach(BiConsumer<? super K, ? super V> action) {
        if (root != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            // 利用层序遍历foreach
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                action.accept(node.key, node.value);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    private Node remove(Node node, K key) {

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                this.size--;
                return null;
            }
            if (node.left == null) {
                node = node.right;
                this.size--;
                return node;
            }

            if (node.right == null) {
                node = node.left;
                this.size--;
                return node;
            }
            // 节点的交换 并不会删除节点
            Node leftTreeMaxNode = leftTreeMaxNode(node.left);
            node.key = leftTreeMaxNode.key;
            node.value = leftTreeMaxNode.value;
            // 删除交换后的节点
            remove(node.left, leftTreeMaxNode.key);
            return node;
        }
        return null;
    }

    private Node leftTreeMaxNode(Node node) {
        return node.right == null ? node : leftTreeMaxNode(node.right);
    }

    public class Node {
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

    public static void main(String[] args) {

        BST<Integer, String> bst = new BST<>();

        bst.put(8, "8");
        bst.put(3, "3");
        bst.put(11, "11");
        bst.put(1, "1");
        bst.put(4, "4");
        bst.put(10, "10");
        bst.put(12, "12");

        System.out.println(bst.size());

        System.out.println(bst.contains(9));

        bst.remove(8);

        System.out.println(bst.size());

        bst.foreach((k, v) -> {
            System.out.println(String.format("key: %s ,value: %s", k, v));
        });

    }

}
