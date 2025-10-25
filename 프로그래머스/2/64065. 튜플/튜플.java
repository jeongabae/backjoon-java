import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 가장 바깥 {{, }} 제거
        s = s.substring(2, s.length() - 2);

        // "},{" 기준으로 split
        String[] parts = s.split("\\},\\{");

        // 길이순으로 정렬
        Arrays.sort(parts, Comparator.comparingInt(String::length));

        List<Integer> ans = new ArrayList<>();
        Set<Integer> v = new HashSet<>();

        for (String part : parts) {
            String[] nums = part.split(",");
            for (String num : nums) {
                int val = Integer.parseInt(num);
                if (!v.contains(val)) {
                    v.add(val);
                    ans.add(val);
                }
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}