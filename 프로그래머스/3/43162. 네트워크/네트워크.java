class Solution {
    boolean[] v;
    int[][] computers;

    public int solution(int n, int[][] computers) {
        v = new boolean[n];
        this.computers = computers;

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                dfs(i);
                cnt++;
            }
        }

        return cnt;
    }

    void dfs(int node) {
        v[node] = true;

        for (int j = 0; j < computers.length; j++) {
            if (computers[node][j] == 1 && !v[j]) {
                dfs(j);
            }
        }
    }
}