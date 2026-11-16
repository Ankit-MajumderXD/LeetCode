
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Boundary elements of both partitions
            int maxLeftX = (partitionX == 0)
                    ? Integer.MIN_VALUE : nums1[partitionX - 1];

            int minRightX = (partitionX == m)
                    ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0)
                    ? Integer.MIN_VALUE : nums2[partitionY - 1];

            int minRightY = (partitionY == n)
                    ? Integer.MAX_VALUE : nums2[partitionY];

            // Correct partition found
            if (maxLeftX <= minRightY &&
                maxLeftY <= minRightX) {

                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftX, maxLeftY);
                }

                // Even total length
                return (
                    Math.max(maxLeftX, maxLeftY) +
                    (double) Math.min(minRightX, minRightY)
                ) / 2.0;
            }

            // Move partition in nums1
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        return 0.0;
    }
}
