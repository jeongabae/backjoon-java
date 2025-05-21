import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int n, m;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], c);
        }

        for (int k = 1; k <= n; k++) { // 경유지
            for (int i = 1; i <= n; i++) { // 출발지
                for (int j = 1; j <= n; j++) { // 도착지
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}