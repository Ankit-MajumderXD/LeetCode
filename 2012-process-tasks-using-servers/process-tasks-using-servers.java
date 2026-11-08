
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: weight, index
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
        );

        // Busy servers: finish time, weight, index
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                if (a[1] != b[1])
                    return a[1] - b[1];
                return a[2] - b[2];
            }
        );

        // Initially, all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int i = 0; i < m; i++) {

            time = Math.max(time, i);

            // Release all servers that have finished
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= time) {

                int[] server = busy.poll();

                available.offer(
                    new int[]{server[1], server[2]}
                );
            }

            // If no server is available, wait
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers finishing at this time
                while (!busy.isEmpty() &&
                       busy.peek()[0] <= time) {

                    int[] server = busy.poll();

                    available.offer(
                        new int[]{server[1], server[2]}
                    );
                }
            }

            // Assign the task to the best available server
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Add server to busy queue
            busy.offer(new int[]{
                (int)(time + tasks[i]),
                weight,
                index
            });
        }

        return ans;
    }
}
