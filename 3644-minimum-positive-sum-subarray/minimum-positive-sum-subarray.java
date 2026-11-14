
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();

        // Build prefix sum array
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int ans = Integer.MAX_VALUE;

        // Check all subarrays
        for (int start = 0; start < n; start++) {
            for (int len = l; len <= r; len++) {
                int end = start + len;

                if (end > n) {
                    break;
                }

                int sum = prefix[end] - prefix[start];

                // Find the minimum positive sum
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
