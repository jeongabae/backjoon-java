class Solution {
    public int solution(int a, int b, int n) {
        int cnt = 0;
        while (n >= a) {
            int coke = (n / a) * b;
            cnt += coke;

            n = coke + (n % a);
        }
        return cnt;
    }
}