import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int ans = 0;

        // 원하는 상품 수량
        Map<String, Integer> need = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            need.put(want[i], number[i]);
        }

        // 현재 10일 윈도우 상품 수량
        Map<String, Integer> cur = new HashMap<>();

        // 초기 10일 세팅
        for (int i = 0; i < 10; i++) {
            cur.put(discount[i], cur.getOrDefault(discount[i], 0) + 1);
        }

        // 첫 구간 검사
        if (check(need, cur)) ans++;

        // 슬라이딩 윈도우
        for (int i = 10; i < discount.length; i++) {
            // 빠지는 상품
            String l = discount[i - 10];
            cur.put(l, cur.get(l) - 1);
            if (cur.get(l) == 0) cur.remove(l);

            // 새로 들어오는 상품
            String r = discount[i];
            cur.put(r, cur.getOrDefault(r, 0) + 1);

            if (check(need, cur)) ans++;
        }

        return ans;
    }

    // 원하는 수량과 현재 수량 비교
    private boolean check(Map<String, Integer> need, Map<String, Integer> cur) {
        for (String key : need.keySet()) {
            if (cur.getOrDefault(key, 0) != need.get(key)) {
                return false;
            }
        }
        return true;
    }
}