package org.iproute.trie;

/**
 * @author zhuzhenjie
 **/
public class Test {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("halo");
        System.out.println(trie.contains("halo1"));
        System.out.println(trie.hasPrefix("h"));
        System.out.println(trie.hasPrefix("m"));
    }

}
