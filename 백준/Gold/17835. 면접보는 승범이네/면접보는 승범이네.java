import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, M, K;
    static List<Node>[] graph;
    static long[] dist;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시
        M = Integer.parseInt(st.nextToken()); //도로
        K = Integer.parseInt(st.nextToken()); //면접장 수

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        dist = new long[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[to].add(new Node(from, cost)); // 역방향!!
        }

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int interviewCity = Integer.parseInt(st.nextToken());
            dist[interviewCity] = 0;
            pq.offer(new Node(interviewCity, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to] < cur.cost) continue;

            for (Node next : graph[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        int city = 0;
        long max = -1;
        for (int i = 1; i <= N; i++) {
            if (dist[i] > max) {
                max = dist[i];
                city = i;
            }
        }

        System.out.println(city);
        System.out.println(max);
    }
}