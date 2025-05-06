import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] parents;
    static List<Edge> edgeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 집 수
            int E = Integer.parseInt(st.nextToken()); // 도로 수

            if (V == 0 && E == 0) break;

            edgeList = new ArrayList<>();
            parents = new int[V];
            for (int i = 0; i < V; i++) parents[i] = i;

            int totalCost = 0;

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edgeList.add(new Edge(from, to, weight));
                totalCost += weight;
            }

            Collections.sort(edgeList); // 비용 기준 정렬

            int mstCost = 0, cnt = 0;
            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    mstCost += edge.weight;
                    if (++cnt == V - 1) break;
                }
            }

            System.out.println(totalCost - mstCost);
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }
}