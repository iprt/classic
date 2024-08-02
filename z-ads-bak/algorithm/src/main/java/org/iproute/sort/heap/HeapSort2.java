package org.iproute.sort.heap;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/26.
 */
public class HeapSort2 {

    public static void sort(Comparable[] arr) {
        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(arr);
        int n = arr.length;
        for(int i=n-1;i>=0;i++){
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        Integer[] arr ={18, 4, 40, 76, 82, 66, 35, 75, 91, 42, 62, 76, 73, 12, 83, 33, 68, 92, 54, 44};
        sort(arr);
        System.out.println(Arrays.toString(arr));


    }



}
