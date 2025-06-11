import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static long[][] dist;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //도시 수
        int E = Integer.parseInt(st.nextToken()); //도로 수
        int K = Integer.parseInt(st.nextToken()); //포장할 도로 수
        
        graph = new ArrayList[N+1];
        dist = new long[N+1][K+1]; //dist[i][k] : i에 도착했을 때 k번 포장썼을 때 최소비용
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c, 0));
            graph[b].add(new Node(a, c, 0));
        }

        dijkstra(K);
        
        long ans = INF;
        for (int i = 0; i <= K; i++) {
			ans = Math.min(ans, dist[N][i]);
		}
        System.out.println(ans);
    }

    static void dijkstra(int K) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1][0] = 0;
        pq.offer(new Node(1,0,0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.v;
            long cost = cur.cost;
            int kCnt = cur.kCnt;

            if (dist[now][kCnt] < cost) continue;

            for (Node next : graph[now]) {
                long newCost = cost + next.cost;
                //포장x
                if (newCost < dist[next.v][kCnt]) {
                    dist[next.v][kCnt] = newCost;
                    pq.offer(new Node(next.v, newCost, kCnt));
                }
                //포장o
                if(kCnt<K && cost<dist[next.v][kCnt+1]) {
                	dist[next.v][kCnt+1] = cost;
                	pq.offer(new Node(next.v, cost, kCnt+1));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int v, kCnt;
        long cost;

        Node(int v, long cost, int kCnt) {
            this.v = v;
            this.cost = cost;
            this.kCnt = kCnt;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}