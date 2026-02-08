import java.io.*;
import java.util.*;

public class Main {

    static int W, H;
    static char[][] map;
    static int[][][] dist;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Node implements Comparable<Node> {
        int x, y, dir, mirror;

        Node(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }

        @Override
        public int compareTo(Node o) {
            return this.mirror - o.mirror;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        dist = new int[H][W][4];

        List<int[]> cList = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                if (map[i][j] == 'C') {
                    cList.add(new int[]{i, j});
                }
            }
        }

        int sy = cList.get(0)[0];
        int sx = cList.get(0)[1];
        int ey = cList.get(1)[0];
        int ex = cList.get(1)[1];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int d = 0; d < 4; d++) {
            dist[sy][sx][d] = 0;
            pq.offer(new Node(sx, sy, d, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.mirror > dist[cur.y][cur.x][cur.dir]) continue;

            for (int nd = 0; nd < 4; nd++) {
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                if (map[ny][nx] == '*') continue;

                int cost = cur.mirror;
                if (cur.dir != nd) cost++;

                if (dist[ny][nx][nd] > cost) {
                    dist[ny][nx][nd] = cost;
                    pq.offer(new Node(nx, ny, nd, cost));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            ans = Math.min(ans, dist[ey][ex][d]);
        }

        System.out.println(ans);
    }
}