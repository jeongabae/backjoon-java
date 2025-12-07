import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int cnt = 0;
        Arrays.sort(d);
        for(int cost:d){
            budget-=cost;
            if(budget<0){
                break;
            }
            cnt++;
        }
        return cnt;
    }
}