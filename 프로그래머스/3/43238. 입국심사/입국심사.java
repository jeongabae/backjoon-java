import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long left = 1;  // 최소 시간
        long right = (long) Arrays.stream(times).max().getAsInt() * n;  // 최대 시간 (최악의 경우)
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            // mid 시간 안에 몇 명 심사 가능한지 계산
            for (int time : times) {
                count += mid / time;
            }

            if (count >= n) {
                // 충분히 심사 가능  시간을 줄여본다
                answer = mid;
                right = mid - 1;
            } else {
                // 부족함  시간을 늘려야 함
                left = mid + 1;
            }
        }

        return answer;
    }
}