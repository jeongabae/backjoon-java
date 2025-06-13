import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] v;
    static int cnt=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 동기 수
        int m = Integer.parseInt(br.readLine()); // 친구 관계 수

        graph = new ArrayList[n+1];
        v = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);
        System.out.println(cnt);
    }

    static void bfs(int start) {
        Queue<int[]> q = new ArrayDeque<>(); //정점번호, 현재깊이 
        v[start] = true;
        q.offer(new int[]{start, 0});


        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int now = cur[0];
            int depth = cur[1];

            if (depth >= 2) continue;

            for (int next : graph[now]) {
                if (!v[next]) {
                    v[next] = true;
                    q.offer(new int[]{next, depth + 1});
                    cnt++;
                }
            }
        }
    }
}