package org.iproute.segment;

import org.omg.CORBA.Object;

/**
 * @author zhuzhenjie
 * 思考 如果让线段树动态扩展
 * 线段树的节点是merge后的内容
 * 线段树的核心 区间问题 创建树以后 囊括了所有区间的内容
 * 1. 线段树的创建 利用递归
 * 2. 线段树的查询 查询和高度有关 和区间长度无关
 **/
public class SegmentTree<E> {

    private E[] tree;

    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        // 为什么开4倍空间
        // 2*k + 1 的思考
        tree = (E[]) new Object[arr.length * 4];

        buildSegmentTree(0, 0, data.length - 1);

    }

    // 在treeIndex的位置创建表示区间[l...r] 的区间数
    // 核心还是队列 和 Comparator有异曲同工之妙
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            // 区间长度为1
            tree[treeIndex] = data[l];
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTeeIndex = rightChild(treeIndex);
        // 分割
        int mid = (l + r) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTeeIndex, mid + 1, r);
        // 综合左右线段的信息 得到更大的线段的信息
        // 业务包含的地方
//        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTeeIndex];
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTeeIndex]);
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    public E query(int queryL, int queryR) {
        // 边界检查
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 还是递归
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            // 到右子树里面查找
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            // 到左子树里面查找
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        // 左孩子包含一部分 右孩子包含一部分
        // 分别处理
        // 两边分别找然后merge
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 辅助函数
    // 返回完全二叉树中一个索引表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树中一个索引表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

}
