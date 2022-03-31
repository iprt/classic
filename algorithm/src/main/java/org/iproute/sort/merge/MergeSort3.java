package org.iproute.sort.merge;

import org.iproute.sort.helper.SortTestHelper;

/**
 * Created by winterfell on 2017/9/24.
 * 自底向上的归并排序
 */
public class MergeSort3 {


    public static void sort(Comparable[] arr){
        mergeSortBU(arr);
    }

    private static void mergeSortBU(Comparable[] arr) {

        int n = arr.length;
        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                // i + sz 保证mid不会越界
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(arr, i, i + sz - 1, min(i + sz + sz - 1,n-1));
            }
        }
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }


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
                i = i + 1;
            } else {
                arr[k] = aux[j - l];
                j = j + 1;
            }
        }
    }

    public static void main(String[] args) {

        SortTestHelper.testSort(MergeSort3.class,SortTestHelper.generateRandomArray(10,0,100));

    }

}
