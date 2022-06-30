package org.iproute.sort.merge;

import org.iproute.sort.insert.InsertionSort;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 * 归并排序的优化方案
 * 1、 当arr[mid] < arr[mid+1]的时候 左边的块与右边的块是有序的状态
 * 2、 当递归的数组小于一定的大小的时候 归并排序的效率不如插入排序
 */
public class MergeSort2 {


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

//        if (l >= r) {
//            return;
//        }
        if((r - l) <= 15){
            // 当递归到一定小的值得时候
            // 插入排序更加有优势
            InsertionSort.sort(arr);
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        // 两边归完成以后进行merge
        // 优化方案
        if(arr[mid].compareTo(arr[mid+1])<0){
            // 因为 mid < mid+1的时候
            // 左边和右边是有序的
            merge(arr, l, mid, r);
        }

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
                j = j+1;
            }
        }
    }

    public static void main(String[] args) {

        Integer[] arr = {6,5,4,3,2,1};

        sort(arr);

        System.out.println(Arrays.toString(arr));

    }

}
