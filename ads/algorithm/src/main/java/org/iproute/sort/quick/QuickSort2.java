package org.iproute.sort.quick;

import org.iproute.sort.helper.SortTestHelper;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 * 随机化快速排序 针对近乎有序的数组
 */
public class QuickSort2 {


    private QuickSort2() {

    }

    // 对arr[l....r] 的部分进行parttion操作
    // 对于返回值p arr[l...p-1] , arr[p+1...r]>arr[p]
    private static int parttion(Comparable[] arr, int l, int r) {

        //随机化 与第一个值交换
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));

        Comparable v = arr[l];
        int j = l;
        // 对于j的定义
        // 如果 j = l ，j就是小的部分的最后一个
        // 如果 j = l+1, j就是大的部分的第一个
        // j = l+1的时候 j-1 永远不会数组越界
        // j必须要比小1 否则会数组越界
        // 为什么j是从l开始
        // 如果j是l+1 在 i 到达最后一个值的时候 j++ 的值可能已经越界了
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                // j 必须从第一个值得后面一个开始
                // j 的位置是一个重点
                // 小于需要比较的值 j的位置 后移以为
                // j是从l 开始的
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, l, j);
        return j;
    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 递归函数体
    private static void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            // 当规模小于一定程度的时候 采用插入排序
            return;
        }
        int p = parttion(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);

    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 闭区间
        quickSort(arr, 0, n - 1);

    }

    public static void main(String[] args) {
        SortTestHelper.testSort(QuickSort.class, SortTestHelper.generateRandomArray(10, 0, 100));
        Integer[] arr = {4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
