import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0; // 각 카드의 긴 변 중 최댓값
        int maxH = 0; // 각 카드의 짧은 변 중 최댓값

        for (int[] s : sizes) {
            int w = Math.max(s[0], s[1]); // 회전해서 긴 쪽을 가로로
            int h = Math.min(s[0], s[1]); // 짧은 쪽을 세로로
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }
        return maxW * maxH; // 필요한 지갑 최소 면적
    }
}
