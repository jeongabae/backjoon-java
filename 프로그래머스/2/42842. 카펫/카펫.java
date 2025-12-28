class Solution {
    public int[] solution(int brown, int yellow) {
        int[] ans = new int[2];
        int total = brown + yellow;

        for (int h = 1; h * h <= total; h++) {
            if (total % h != 0) continue;

            int w = total / h;
            if (w < h) continue;

            if ((w-2) * (h-2) == yellow) {
                ans[0] = w;
                ans[1] = h;
                break;
            }
        }
        return ans;
    }
}