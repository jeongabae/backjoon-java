import java.util.*;

class Solution {
    int[] p;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(a->a[2]));
        
        p = new int[n];
        for(int i=0;i<n;i++) p[i] = i;
        
        int totalCost = 0;
        for(int[] edge:costs){
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];
            
            if(find(a)!=find(b)){
                union(a,b);
                totalCost += cost;
            }
        }
        return totalCost;
    }
    
    int find(int x){
        if(p[x]!=x) p[x] = find(p[x]);
        return p[x];
    }
    
    void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA < rootB) p[rootB] = rootA;
        else p[rootA] = rootB;
    }
}