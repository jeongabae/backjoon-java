import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N;
    static int[][] cheese;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            int maxCnt = 1;
 
            for (int d = 0; d <= 100; d++) {
                visited = new boolean[N][N];
                int cnt = 0;
 
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && cheese[i][j] > d) {
                            bfs(i, j, d);
                            cnt++;
                        }
                    }
                }
 
                maxCnt = Math.max(maxCnt, cnt);
            }
 
            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }
 
        System.out.print(sb.toString());
    }
 
    private static void bfs(int x, int y, int d) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
 
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
 
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
 
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && cheese[nx][ny] > d) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}