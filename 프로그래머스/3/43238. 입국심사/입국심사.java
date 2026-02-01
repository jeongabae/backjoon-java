import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long l = 1;
        long r = 0;

        for (int time : times) {
            r = Math.max(r, time);
        }
        r *= n;
        
        long ans = r;

        while (l <= r) {
            long mid = (l + r) / 2;

            long cnt = 0;
            for (int time : times) {
                cnt += mid / time;
                if (cnt >= n) break;
            }

            if (cnt >= n) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}