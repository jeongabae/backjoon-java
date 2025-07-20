import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] ans = new int[commands.length];

        for (int i=0; i < commands.length; i++) {
            int a = commands[i][0];
            int b = commands[i][1];
            int c = commands[i][2];

            int[] subArr = Arrays.copyOfRange(array,a-1,b);
            Arrays.sort(subArr);
            
            ans[i] = subArr[c-1];
        }

        return ans;
    }
}