
import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    static final int LOG = 18;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        // Build adjacency list
        List<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] depth = new int[n + 1];
        int[][] up = new int[LOG][n + 1];

        // BFS to calculate depth and immediate parent
        int[] queue = new int[n];
        int front = 0, rear = 0;

        queue[rear++] = 1;

        while (front < rear) {
            int u = queue[front++];

            for (int v : adj[u]) {
                if (v == up[0][u]) {
                    continue;
                }

                up[0][v] = u;
                depth[v] = depth[u] + 1;

                queue[rear++] = v;
            }
        }

        // Binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Precompute powers of 2
        long[] pow2 = new long[n + 1];

        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int[] ans = new int[queries.length];

        // Process queries
        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = findLCA(u, v, depth, up);

            int distance = depth[u] + depth[v]
                         - 2 * depth[lca];

            if (distance == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow2[distance - 1];
            }
        }

        return ans;
    }

    // Find Lowest Common Ancestor
    private int findLCA(int u, int v,
                        int[] depth, int[][] up) {

        // Make u the deeper node
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u to the same depth as v
        int diff = depth[u] - depth[v];

        for (int j = LOG - 1; j >= 0; j--) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both nodes together
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
