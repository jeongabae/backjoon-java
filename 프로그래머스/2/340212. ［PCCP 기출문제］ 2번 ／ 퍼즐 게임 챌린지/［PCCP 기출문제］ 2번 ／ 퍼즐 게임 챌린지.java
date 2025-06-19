class Solution {
    static boolean canClear(int[] diffs, int[] times, long limit, int level){
        long totalTime = 0;
        
        for(int i=0;i<diffs.length;i++){
            if(i==0||diffs[i]<=level){
                totalTime+=times[i];
            }else{
                totalTime += (times[i]+times[i-1])*(diffs[i]-level)+times[i];
            }
            
            if (totalTime>limit) return false;
        }
        return true;
    }
    public int solution(int[] diffs, int[] times, long limit) {
        int l = 1;
        int r = 100000;
        int ans = r;
        
        while(l<=r){
            int m = (l+r)/2;
            if(canClear(diffs, times, limit, m)){
                ans = m;
                r = m-1;
            }else{
                l=m+1;
            }
        }
        
        return ans;
    }
    
}