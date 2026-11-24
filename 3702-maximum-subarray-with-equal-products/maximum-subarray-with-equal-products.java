
class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {

                int num = nums[j];

                // Update product
                product *= num;

                // Update GCD
                gcd = findGCD(gcd, num);

                // Update LCM
                lcm = (lcm / findGCD(lcm, num)) * num;

                // Check product equivalence
                if (product == gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long findGCD(long a, long b) {

        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
