package org.iproute.sort.merge;

import org.iproute.sort.helper.SortTestHelper;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 */
public class MergeSort {


    public static void sort(Comparable[] arr) {
        int n = arr.length;
        mergeSort(arr,0,n-1);
    }

    /**
     * 递归的思想
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(Comparable[] arr, int l, int r) {

        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        // 两边归完成以后进行merge
        merge(arr, l, mid, r);
    }

    // 将arr[L...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        // 临时空间
        Comparable[] aux = new Comparable[r - l + 1];

        for (int i = l; i <= r; i++) {
            // l是偏移量
            aux[i - l] = arr[i];
        }
        // i 和 j指向两个子数组开头的位置
        int i = l, j = mid + 1;

        for (int k = l; k <= r; k++) {
            // 归并每次决定arr[k] 的位置究竟是谁
            // l是偏移量
            if (i > mid) {  //当左边部分已经跑完的时候 此时i已经无效了
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { // 同理 j无效了
                arr[k] = aux[i - l];
                i++;
            }
            // i 和 j 都是有效的
            else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {

        Integer[] arr = {8, 6, 5, 1, 2, 4};

        MergeSort.sort(arr);

        System.out.println(Arrays.toString(arr));


        int n =50000;
        Integer[] test = SortTestHelper.generateRandomArray(n,0,n);
        SortTestHelper.testSort(MergeSort.class,test);

    }

}
