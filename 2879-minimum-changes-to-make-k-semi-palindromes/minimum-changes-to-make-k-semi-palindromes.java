
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1_000_000_000;

        // Step 1: Precompute semi-palindrome conversion costs
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {

            for (int start = 0; start + len <= n; start++) {

                int end = start + len - 1;
                int minChanges = INF;

                // Try every proper divisor of the substring length
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int offset = 0; offset < d; offset++) {

                        int groupSize = (len - 1 - offset) / d + 1;

                        // Make each group a palindrome
                        for (int t = 0; t < groupSize / 2; t++) {

                            int left = start + offset + t * d;

                            int right = start + offset
                                    + (groupSize - 1 - t) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    minChanges = Math.min(minChanges, changes);
                }

                cost[start][end] = minChanges;
            }
        }

        // Step 2: Dynamic Programming
        // dp[p][i] = minimum changes to divide first i characters
        // into exactly p semi-palindromes

        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                // Previous partition must leave at least 2 characters
                // for the current substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
