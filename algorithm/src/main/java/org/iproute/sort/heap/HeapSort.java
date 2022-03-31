package org.iproute.sort.heap;

import org.iproute.sort.helper.SortTestHelper;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/26.
 * 原地堆排序
 * 利用shiftDown的操作
 * 同时把数组每个最大的值放到数组的最后面
 */
public class HeapSort {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        //heapify 变成一棵完全二叉树
        // 最后 一个元素的索引是n-1
        // maxIndex = 7, n=8 ,LastFatherIndex 3
        // maxIndex = 8, n=9 ,LastFatherIndex 3
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            // n为数组大小
            shiftDown(arr, n, i);
//            System.out.println(Arrays.toString(arr));
        }

        for (int i = n - 1; i > 0; i--) {
            // n-1 是数组的最后一位
            // 最后一个和第一个交换位置 最后一个是最大值
            swap(arr, 0, i);
            // 再次shiftDown
            // 传入整个数组，对数组处理部分处理
            // 为什么不能对 i-1 进行shiftDown
            // n -1 数组的个数已经 -1
            // n表示的是数组的个数
            // 个数与索引之间的关系一定要搞清楚
            shiftDown(arr, i , 0);
        }
    }

    /**
     * shifdown的本质是把大数向下排
     * @param arr 数组
     * @param n   数组的个数 不是数组的最大索引
     * @param k   shiftDown哪个位置的元素
     */
    private static void shiftDown(Comparable[] arr, int n, int k) {
        
        while (2 * k + 1 < n) { // 保证有子节点
            int j = 2 * k + 1; //做节点
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {
                // 有左有右 右大 取右
                j += 1;
            }

            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    private static void swap(Comparable[] arr, int x, int y) {
        Comparable t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {
        int n = 50;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        SortTestHelper.testSort(HeapSort.class, arr);
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(Arrays.toString(arr));
        
    }
}
