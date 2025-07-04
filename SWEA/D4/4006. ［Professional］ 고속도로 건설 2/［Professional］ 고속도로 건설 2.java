import java.io.*;
import java.util.*;

public class Solution {
    static int[] p;

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge target) {
            return this.cost - target.cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            p = new int[N+1];
            for(int i=1; i<=N; i++) p[i] = i;

            int M = Integer.parseInt(br.readLine());
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pq.add(new Edge(s, e, c));
            }

            long ans = 0;
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                int a = find(e.from);
                int b = find(e.to);
                if (a == b) continue;
                union(a, b);
                ans += e.cost;
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) p[b] = a;
        else p[a] = b;
    }

    static int find(int x) {
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }
}