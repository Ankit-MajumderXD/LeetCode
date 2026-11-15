
class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;

        long even = 1;
        long odd = 0;
        long count = 0;

        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                count += odd;
                even++;
            } else {
                count += even;
                odd++;
            }

            count %= MOD;
        }

        return (int) count;
    }
}
