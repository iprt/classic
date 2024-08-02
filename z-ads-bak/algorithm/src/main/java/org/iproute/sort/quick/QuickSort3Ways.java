package org.iproute.sort.quick;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 * 3路快速排序
 *
 * 维护索引的操作是很重要的东西
 */
public class QuickSort3Ways {

    private static void sort(Comparable[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        Comparable v = arr[l];

        int lt = l; // arr[l+1...lt] <v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1; // arr[lt+1...i] == v
        while (i < gt) {

            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, l, lt);

        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    public static void main(String[] args) {

        Integer[] arr = {4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
