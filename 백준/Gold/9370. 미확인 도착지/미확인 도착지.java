import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static final int INF = 1_000_000_000;
    static int T, n, m, t, s, g, h;
    static List<Node>[] graph;
    static List<Integer> destList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            int ghDist = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    ghDist = d;
                }
            }

            destList = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                destList.add(Integer.parseInt(br.readLine()));
            }

            int[] distS = dijkstra(s);
            int[] distG = dijkstra(g);
            int[] distH = dijkstra(h);

            List<Integer> result = new ArrayList<>();
            for (int x : destList) {
                int path1 = distS[g] + ghDist + distH[x];
                int path2 = distS[h] + ghDist + distG[x];

                if (distS[x] == path1 || distS[x] == path2) {
                    result.add(x);
                }
            }

            Collections.sort(result);
            for (int x : result) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.to] < cur.cost) continue;

            for (Node next : graph[cur.to]) {
                int newDist = cur.cost + next.cost;
                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    pq.offer(new Node(next.to, newDist));
                }
            }
        }

        return dist;
    }
}