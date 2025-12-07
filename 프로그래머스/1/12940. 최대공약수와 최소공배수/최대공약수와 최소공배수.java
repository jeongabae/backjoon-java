class Solution {
    public int[] solution(int n, int m) {
        int g = gcd(n, m);
        int l = n / g * m;
        return new int[]{g, l};
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}