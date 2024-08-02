package org.iproute.dynamicprogramming;

/**
 * @author tech@intellij.io
 */
public class ClassicDemo1 {

    // 动态规划的求解思路
    // 定义状态
    // 定义状态转移方程

    // 最大子序列问题的动态规划
    // 定义状态:定义一个数字maxSum[n]
    // 其中 maxSum[i] 表示 [0...i]中的最大子序
    // 状态转移方程:maxSum[i+1] = max{(max[i]+arr[i+1]),arr[i+1]} 贪心的思路
    // 如果 arr[i+1] > maxSum[i+1] 的值 则表示 arr[i+1] 为新的内容

    public static void main(String[] args) {
        int[] arr = {-1, 2, 3, 4, -7, 6, 8, -7};
        System.out.println(solve(arr));
    }

    /**
     * 求arr的最大子序列
     */
    public static int solve(int[] arr) {
        int len = arr.length;
        int[] maxSum = new int[len];
        int res = Integer.MIN_VALUE;
        maxSum[0] = arr[0];
        for (int i = 1; i < len; i++) {
            maxSum[i] = Math.max(maxSum[i - 1] + arr[i], arr[i]);
            if (maxSum[i] > res) {
                res = maxSum[i];
            }
        }
        return res;
    }
}