import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n+1];
        int[] dist = new int[n+1]; //각 노드까지 거리
        int maxD = 0;
        int cnt = 0;
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            int a = e[0];
            int b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        dist[1] = 1;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next:graph[cur]){
                if(dist[next]==0){
                    dist[next] = dist[cur]+1;
                    q.offer(next);
                }
            }
        }
        
        for(int d:dist){
            if(d>maxD) maxD = d;
        }
        
        for(int d:dist){
            if(d==maxD) cnt++;
        }
        
        return cnt;
    }
}