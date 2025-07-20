class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0;
        int maxH = 0;

        for (int[] card : sizes) {
            int w = Math.max(card[0], card[1]); // 더 큰 쪽 가로로
            int h = Math.min(card[0], card[1]); // 더 작은 쪽 세로로

            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }

        return maxW * maxH;
    }
}