package dp.other.linear.other;

/**
 * 53
 *
 * =====
 *
 * Reference.
 *
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class MaximumSubarray {

  /**
   * Kadane's algorithm. We replace running sum with current element if current element is bigger
   * than running sum.
   */
  public static class Solution {

    public int maxSubArray(int[] nums) {
      int curSum = 0;
      int ans = Integer.MIN_VALUE;
      for (int i = 0; i < nums.length; i++) {
        curSum += nums[i];
        if (nums[i] > curSum) {
          curSum = nums[i];
        }
        ans = Math.max(curSum, ans);
      }
      return ans;
    }
  }

  class Solution2 {

    public int maxSubArray(int[] nums) {
      int sum = 0, ans = Integer.MIN_VALUE;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] + sum < nums[i]) {
          sum = nums[i];
        } else {
          sum += nums[i];
        }
        ans = Math.max(ans, sum);
      }
      return ans;
    }
  }
}