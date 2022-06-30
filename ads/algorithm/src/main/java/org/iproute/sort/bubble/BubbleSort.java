package org.iproute.sort.bubble;

import org.iproute.sort.helper.SortTestHelper;

/**
 * Created by winterfell on 2017/9/24.
 */
public class BubbleSort {


    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(Comparable[] arr, int x, int y) {
        Comparable t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {

        int n = 500;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        SortTestHelper.testSort(BubbleSort.class,arr);
        System.out.println(SortTestHelper.isSorted(arr));

    }
}
