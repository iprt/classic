package org.iproute.search;

/**
 * Created by winterfell on 2017/9/29.
 * 二分查找法 针对的是有序的数组
 * <p>
 * 定义好变量的边界
 */
public class BinarySearch {

    // 迭代的版本
    public static int binarySearch(Comparable[] arr, Comparable target) {
        int n = arr.length;
        // 在arr[l,r]之中查找target
        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            // 边界问题
            if (target.compareTo(arr[mid]) < 0) {
                // 在[l,mid-1]之中查找target
                r = mid - 1;
            } else {
                // 在[mid+1,r]之中查找target
                l = mid + 1;
            }
        }
        return -1;
    }

    // 递归的版本
    public static int binarySearch2(Comparable[] arr, Comparable target) {
        int n = arr.length;
        int l = 0, r = n - 1;
        return _binarySearch2(arr, l, r, target);
    }

    static int _binarySearch2(Comparable[] arr, int l, int r, Comparable target) {

        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (target.compareTo(arr[mid]) < 0) {
                return _binarySearch2(arr, l, mid - 1, target);
            } else {
                return _binarySearch2(arr, mid + 1, r, target);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 4, 5, 6, 7, 8};
        System.out.println(binarySearch(arr, new Integer(4)));
        System.out.println(binarySearch2(arr, new Integer(4)));
    }


}
