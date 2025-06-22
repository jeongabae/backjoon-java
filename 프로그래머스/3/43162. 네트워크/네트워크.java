class Solution {
    boolean[] v;
    int cnt;
    
    public int solution(int n, int[][] computers) {
        v = new boolean[n];
        cnt = 0;
        for(int i=0;i<n;i++){
                if(!v[i]){
                    dfs(i, computers);
                    cnt++;
                }
                
            }
        return cnt;
    }
    
    void dfs(int node, int[][] computers){
        v[node] = true;
        
        for(int j=0;j<computers.length;j++){
            if(node!=j&&computers[node][j]==1&&!v[j]){
                dfs(j,computers);
            }
        }
    }    
}