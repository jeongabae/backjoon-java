import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] cost;
    static boolean[] v;
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
        // 비용이 적은 친구가 부모!
        if (cost[rootA] < cost[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        int K = Integer.parseInt(st.nextToken()); // 돈

        cost = new int[N+1];
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        int minCost = 0;
        v = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            if (!v[root]) {
                minCost += cost[root];
                v[root] = true;
            }
        }

        if (minCost <= K) {
            System.out.println(minCost);
        } else {
            System.out.println("Oh no");
        }
    }
}