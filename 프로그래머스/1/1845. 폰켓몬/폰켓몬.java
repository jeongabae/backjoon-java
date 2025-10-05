import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        
        //폰켓몬 종류 저장
        for (int n : nums) {
            hs.add(n);
        }
        //종류 수 or N/2(고를 수 있는 수)중에 더 작은 값이 답
        return Math.min(hs.size(), nums.length / 2);
    }
}