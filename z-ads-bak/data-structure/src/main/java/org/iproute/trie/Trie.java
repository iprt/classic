package org.iproute.trie;

import java.util.TreeMap;

/**
 * @author zhuzhenjie
 **/
public class Trie {
    private class Node {
        boolean isWord;
        TreeMap<Character, Node> next;

        Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        Node() {
            this(false);
        }
    }

    private Node root;

    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    public int size() {
        return size;
    }

    // 添加一个新的单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // c 作为节点插入
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            // 节点在变化
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 查询单词是否在trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // !!! 前缀搜索 是否包含前缀
    public boolean hasPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
