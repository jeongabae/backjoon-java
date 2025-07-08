import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        int[] cnt = new int[N + 1];
        int maxCnt = 0;
        for (int i = 1; i <= N; i++) {
            cnt[i] = bfs(i);
            maxCnt = Math.max(maxCnt, cnt[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(cnt[i]==maxCnt){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }

    static int bfs(int i) {
        Queue<Integer> q = new ArrayDeque<>();
        v = new boolean[N + 1];
        int cnt = 1;
        q.offer(i);
        v[i] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next:graph[cur]){
                if(!v[next]){
                    q.offer(next);
                    v[next] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}