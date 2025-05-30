import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !v[i][j]) {
                    int area = bfs(i, j);
                    cnt++;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxArea);
    }

    static int bfs(int x, int y) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        v[x][y] = true;
        int area = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 1 && !v[nx][ny]) {
                        v[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        area++;
                    }
                }
            }
        }

        return area;
    }
}