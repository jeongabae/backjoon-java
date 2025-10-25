import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();

        // 1. 사전 초기화
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char)('A'+i)), i+1);
        }

        int idx = 27;
        int i = 0; // 현재 위치
        while (i < msg.length()) {
            String w = "" + msg.charAt(i);
            int j = i + 1;

            // 2. 가장 긴 w 찾기
            while (j <= msg.length() && dict.containsKey(msg.substring(i, j))) {
                w = msg.substring(i, j);
                j++;
            }

            // 3. w의 인덱스 추가
            ans.add(dict.get(w));

            // 4. 사전에 w+c 등록 (입력 남아 있을 때만)
            if (j <= msg.length()) {
                String newWord = msg.substring(i, j);
                dict.put(newWord, idx++);
            }

            // 5. 다음 위치로 이동
            i += w.length();
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}