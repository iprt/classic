package org.iproute.sort.insert;

import org.iproute.sort.helper.SortTestHelper;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 */
public class InsertionSort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 寻找元素arr[i]合适的插入位置
//            for (int j = i; j > 0; j--) {
//                if(arr[j].compareTo(arr[j-1])<0 ){
//                    swap(arr,j,j-1);
//                }else{
//                    // 一开始没有理解为什么是break
//                    // 因为从后向前只要不是最小的 前面的就已经是有序的了
//                    break;
//                }
//            }

            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }


    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private InsertionSort() {
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 8, 1, 4, 2};
        InsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        SortTestHelper.testSort(InsertionSort.class,arr);

    }

}
