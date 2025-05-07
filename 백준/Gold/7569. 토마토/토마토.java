import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static boolean[][][] v;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        v = new boolean[H][N][M];

        Queue<int[]> q = new ArrayDeque<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[h][i][j] = Integer.parseInt(st.nextToken());
                    if (box[h][i][j] == 1) {
                        q.offer(new int[]{h, i, j, 0});
                        v[h][i][j] = true;
                    }
                }
            }
        }

        int maxDay = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int z = now[0], x = now[1], y = now[2], day = now[3];
            maxDay = Math.max(maxDay, day);

            for (int d = 0; d < 6; d++) {
                int nz = z + dz[d];
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (0 <= nz && nz < H && 0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (!v[nz][nx][ny] && box[nz][nx][ny] == 0) {
                        v[nz][nx][ny] = true;
                        q.offer(new int[]{nz, nx, ny, day + 1});
                    }
                }
            }
        }

        // 모든 칸 확인
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (box[h][i][j] == 0 && !v[h][i][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(maxDay);
    }
}