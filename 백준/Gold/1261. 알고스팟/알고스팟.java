import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] map;
    static int[][] dist;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[N - 1][M - 1]);
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] now = q.pollFirst(); // 맨 앞에서 꺼냄

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    int cost = map[nx][ny];
                    if (dist[nx][ny] > dist[now[0]][now[1]] + cost) {
                        dist[nx][ny] = dist[now[0]][now[1]] + cost;
                        if (cost == 1) {
                            q.addLast(new int[]{nx, ny}); // 벽은 나중에 탐색
                        } else {
                            q.addFirst(new int[]{nx, ny}); // 빈칸은 먼저 탐색
                        }
                    }
                }
            }
        }
    }
}