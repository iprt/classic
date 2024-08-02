package org.iproute.exchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exchange {

    public static void main(String[] args) {
        int num = 100;

        int total = 100;

        int[] persons = new int[num];
        Arrays.fill(persons, total);

        // 交换次数
        int exchangeNum = 10000;

        // 方差统计间隔
        int statistic = 1000;

        // 计算方差
        List<Integer> fc = new ArrayList<>();

        for (int i = 1; i <= exchangeNum; i++) {
            int x = generateRandom(0, num - 1);
            int y = generateRandom(0, num - 1);

            if (x == y)
                continue;
            else if (persons[x] >= 1) {
                persons[x] -= 1;
                persons[y] += 1;
            }
            if (i / statistic == 0) {
                int fcNum = 0;
                for (int k = 0; k < num; k++) {
                    fcNum += (persons[k] - total) * (persons[k] - total);
                    fc.add(fcNum);
                }
            }
        }
        System.out.println(fc.size());
//        fc.forEach(t -> System.out.println(t));
    }


    public static int generateRandom(int from, int to) {
        if (to < from) {
            return -1;
        }
//        [0,1)
//        [from,to]
        int random = from + (int) ((to - from + 1) * Math.random());
        return random;
    }


    public static void sort(int[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j >= l + 1 && arr[j] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
