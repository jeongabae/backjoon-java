import java.util.*;

class Solution {
    List<Integer>[] graph;
    boolean[] v;

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] w : wires) {
            graph[w[0]].add(w[1]);
            graph[w[1]].add(w[0]);
        }

        int ans = Integer.MAX_VALUE;

        for (int[] w : wires) {
            v = new boolean[n + 1];
            int cnt = dfs(w[0], w[0], w[1]);
            ans = Math.min(ans, Math.abs(cnt - (n - cnt)));
        }

        return ans;
    }

    int dfs(int cur, int a, int b) {
        v[cur] = true;
        int cnt = 1;

        for (int next : graph[cur]) {
            if ((cur == a && next == b) || (cur == b && next == a)) continue;
            if (!v[next]) cnt += dfs(next, a, b);
        }

        return cnt;
    }
}