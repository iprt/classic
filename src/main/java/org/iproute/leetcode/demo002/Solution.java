package org.iproute.leetcode.demo002;

/**
 * Solution
 *
 * @author winterfell
 * @since 2021/11/23
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        // 起点
        int res = nums[0];
        // 总和
        int sum = 0;

        for (int num : nums) {
            // 本质上只需要关心和
            if (sum > 0) {
                // sum > 0 规避掉了 nums[0]
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }


    public static void main(String[] args) {

        int i = new Solution().maxSubArray(new int[]{-1, -2, 3, -4, 5});

        System.out.println(i);

    }
}
/*

本质上只需要关心和，考虑到全部是负数

定义 f(0) = nums[0]  sum = 0

f(n) = f(f[n-1]+nums[n],nums[n])

 */