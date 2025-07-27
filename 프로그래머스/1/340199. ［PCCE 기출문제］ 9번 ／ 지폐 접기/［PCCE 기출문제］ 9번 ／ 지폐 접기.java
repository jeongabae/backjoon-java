class Solution {
    public int solution(int[] wallet, int[] bill) {
        int cnt = 0;

        // 계속 반복 -> 지갑에 들어갈 수 있게..
        while (true) {
            // 지갑과 지폐를 각각 정렬 비교 (작은 쪽 기준..)
            int[] w = wallet.clone();
            int[] b = bill.clone();
            java.util.Arrays.sort(w);
            java.util.Arrays.sort(b);

            // 지갑보다 지폐가 작거나 같으면 넣을 수 있으니까 ㅇㅋ?
            if (b[0] <= w[0] && b[1] <= w[1]) break;

            // 긴 변을 반으로 접기
            if (bill[0] >= bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }

            cnt++;
        }

        return cnt;
    }
}