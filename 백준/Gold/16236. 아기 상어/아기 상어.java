import java.io.*;
import java.util.*;

public class Main {
    static int n, curSize = 2, curX, curY, time = 0, ate = 0;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    curX = i;
                    curY = j;
                    arr[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] fishList = bfs();
            if (fishList == null) break;

            curX = fishList[0];
            curY = fishList[1];
            time += fishList[2];
            arr[curX][curY] = 0;
            ate++;

            if (ate == curSize) {
                curSize++;
                ate = 0;
            }
        }

        System.out.println(time);
    }

    static int[] bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{curX, curY, 0});

        int minX = -1, minY = -1, minDist = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[n][n];
        visited[curX][curY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (arr[x][y] > 0 && arr[x][y] < curSize) {
                if (dist < minDist || (dist == minDist && (x < minX || (x == minX && y < minY)))) {
                    minX = x;
                    minY = y;
                    minDist = dist;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && arr[nx][ny] <= curSize) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return minX == -1 ? null : new int[]{minX, minY, minDist};
    }
}