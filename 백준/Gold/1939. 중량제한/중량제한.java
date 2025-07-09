import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static class Node implements Comparable<Node>{
        int from, to;
        long weight;
        Node(int from,  int to, long weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        public int compareTo(Node o){
            return Long.compare(o.weight,this.weight);
        }
    }
    static int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        parent[rootB] = rootA;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬 개수
        int M = Integer.parseInt(st.nextToken()); // 다리 개수

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long C = Integer.parseInt(st.nextToken());
            graph.add(new Node(a, b, C));
        }

        Collections.sort(graph);

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        for (Node i : graph) {
            union(i.from, i.to);
            if(find(s)==find(e)){
                System.out.println(i.weight);
                return;
            }
        }
    }
}