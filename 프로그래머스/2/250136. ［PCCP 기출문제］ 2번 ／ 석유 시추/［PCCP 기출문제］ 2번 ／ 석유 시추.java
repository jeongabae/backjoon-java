import java.util.*;

class Solution {
    int[][] arr;
    boolean[][] v;
    int n, m;
    int[] oilPerCol;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    public int solution(int[][] land) {
        this.arr = land;
        n = arr.length;
        m = arr[0].length;
        v = new boolean[n][m];
        oilPerCol = new int[m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!v[i][j]&&arr[i][j]==1){
                    Set<Integer> colSet = new HashSet<>();
                    int size = bfs(i,j,colSet);
                    
                    for(int c:colSet){
                        oilPerCol[c]+=size;
                    }
                }
            }
        }
        
        int max=0;
        for(int cSum:oilPerCol){
            max = Math.max(max,cSum);
        }
        
        return max;
    }
    
    private int bfs(int x, int y, Set<Integer> colSet){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        v[x][y] = true;
        colSet.add(y);
        int cnt = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for(int d=0;d<4;d++){
                int nx = cx+dx[d];
                int ny = cy+dy[d];
                if(0<=nx&&nx<n&&0<=ny&&ny<m&&!v[nx][ny]&&arr[nx][ny]==1){
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                    colSet.add(ny);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}