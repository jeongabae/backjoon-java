import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a,b) -> a[1]-b[1]);

        int ans = 0;
        // 마지막으로 요격한 x 좌표
        double cur = -1;

        for (int[] t : targets) {
            int s = t[0];
            int e = t[1];

            // 현재 요격 위치가 (s, e)에 포함되지 않으면
            if (!(s < cur && cur < e)) {
                // e 바로 직전에 요격!!
                cur = e - 0.5;
                ans++;
            }
        }

        return ans;
    }
}