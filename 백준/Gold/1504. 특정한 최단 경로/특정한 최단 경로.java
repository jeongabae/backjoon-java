import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1, N);
        int[] distFromV1 = dijkstra(v1, N);
        int[] distFromV2 = dijkstra(v2, N);

        // 경로 1: 1, v1, v2, N
        long route1 = (long)distFrom1[v1] + distFromV1[v2] + distFromV2[N];

        // 경로 2: 1, v2, v1, N
        long route2 = (long)distFrom1[v2] + distFromV2[v1] + distFromV1[N];

        long ans = Math.min(route1, route2);

        // 경로가 하나라도 INF이면 불가능
        System.out.println(ans >= INF ? -1 : ans);
    }

    static int[] dijkstra(int start, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.v;
            int cost = cur.cost;

            if (dist[now] < cost) continue;

            for (Node next : graph[now]) {
                int newCost = dist[now] + next.cost;
                if (newCost < dist[next.v]) {
                    dist[next.v] = newCost;
                    pq.offer(new Node(next.v, newCost));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int v, cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}