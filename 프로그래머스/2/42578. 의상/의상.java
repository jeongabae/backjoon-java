import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 의상 종류, 해당 종류의 의상 수
        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        int ans = 1;
        //각 의상 종류의 수 + 안입는 경우(1) 까지해서 곱하기
        for (int cnt : map.values()) {
            ans *= (cnt + 1);
        }

        // 아무것도 안 입는 경우 1 빼기
        return ans - 1;
    }
}
