class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n + 1][n + 1];

        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];
            graph[win][lose] = 1;   // win이 lose를 이김
            graph[lose][win] = -1;  // lose는 win에게 짐
        }

        // 플로이드 워셜 ㄱㄱ
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    //i가 k에게 이기고 k가 j에게 이기면 i는 j한테 이김
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                    //i가 k한테 지고 k가 j한테 지면 i가 j한테 짐
                    if (graph[i][k] == -1 && graph[k][j] == -1) {
                        graph[i][j] = -1;
                        graph[j][i] = 1;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int known = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue; //자기는 빼고~
                if (graph[i][j] != 0) known++; //승패 결정됨
            }
            // 자신 제외, 모든 선수와의 승패가 정해졌다면 순위를 알기 ㄱㄴ
            if (known == n - 1) cnt++;
        }

        return cnt;
    }
}