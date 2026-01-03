class Solution {
    boolean[] v;
    int ans;
    public int solution(int k, int[][] dungeons) {
        v = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return ans;
    }
    
    void dfs(int cur, int[][] d, int cnt){
        ans = Math.max(ans, cnt);
        
        for(int i=0; i<d.length; i++){
            if(v[i]) continue;
            
            int need = d[i][0];
            int cost = d[i][1];
            
            if(need<=cur){
                v[i] = true;
                dfs(cur-cost,d,cnt+1);
                v[i] = false;
            }
        }
    }
}