
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        int[] stack = new int[n];
        int top = -1;

        int[] next = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = top == -1 ? n : stack[top];
            stack[++top] = i;
        }

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n + 1];
        long[][] cost = new long[LOG][n + 1];

        for (int i = 0; i <= n; i++) {
            up[0][i] = n;
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];

            if (next[i] < n) {
                cost[0][i] =
                    (long) nums[i] * (next[i] - i)
                    - (prefix[next[i]] - prefix[i]);
            }
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i <= n; i++) {

                int mid = up[j - 1][i];

                up[j][i] = up[j - 1][mid];

                cost[j][i] =
                    cost[j - 1][i] + cost[j - 1][mid];
            }
        }

        int left = 0;
        long answer = 0;

        for (int right = 0; right < n; right++) {

            int current = left;
            long operations = 0;

            for (int j = LOG - 1; j >= 0; j--) {
                int nextIndex = up[j][current];

                if (nextIndex <= right) {
                    operations += cost[j][current];
                    current = nextIndex;
                }
            }

            operations += (long) nums[current] *
                    (right - current + 1)
                    - (prefix[right + 1] - prefix[current]);

            while (operations > k) {
                left++;

                current = left;
                operations = 0;

                for (int j = LOG - 1; j >= 0; j--) {
                    int nextIndex = up[j][current];

                    if (nextIndex <= right) {
                        operations += cost[j][current];
                        current = nextIndex;
                    }
                }

                operations += (long) nums[current] *
                        (right - current + 1)
                        - (prefix[right + 1] - prefix[current]);
            }

            answer += right - left + 1;
        }

        return answer;
    }
}
