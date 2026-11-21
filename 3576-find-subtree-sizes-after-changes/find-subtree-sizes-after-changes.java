
import java.util.*;

class Solution {

    List<Integer>[] tree;
    List<Integer>[] newTree;

    int[] last;
    int[] newParent;
    int[] answer;
    int[] originalParent;

    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;
        this.s = s;
        this.originalParent = parent;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        newParent = new int[n];
        newParent[0] = -1;

        // Find new parents
        dfsFind(0);

        // Build modified tree
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        answer = new int[n];

        // Calculate subtree sizes
        dfsSize(0);

        return answer;
    }

    void dfsFind(int node) {

        int ch = s.charAt(node) - 'a';

        int previous = last[ch];

        if (node != 0 && previous != -1) {
            newParent[node] = previous;
        } else if (node != 0) {
            newParent[node] = originalParent[node];
        }

        last[ch] = node;

        for (int child : tree[node]) {
            dfsFind(child);
        }

        // Restore previous ancestor
        last[ch] = previous;
    }

    int dfsSize(int node) {

        int size = 1;

        for (int child : newTree[node]) {
            size += dfsSize(child);
        }

        answer[node] = size;

        return size;
    }
}
