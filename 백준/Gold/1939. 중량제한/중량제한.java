import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static boolean[] v;

    static class Node implements Comparable<Node> {
        int to;
        long weight;

        Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Long.compare(o.weight, this.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        v = new boolean[N+1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long C = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, C));
            graph[b].add(new Node(a, C));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, Long.MAX_VALUE));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            long w = node.weight;

            if (cur == e) {
                System.out.println(w);
                return;
            }

            if (!v[cur]) {
                v[cur] = true;
                for (Node next : graph[cur]) {
                    if (!v[next.to]) {
                        pq.offer(new Node(next.to, Math.min(w, next.weight)));
                    }
                }
            }
        }
    }
}