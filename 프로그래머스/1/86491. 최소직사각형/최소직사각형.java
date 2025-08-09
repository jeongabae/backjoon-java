import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = Arrays.stream(sizes)
                         .mapToInt(a -> Math.max(a[0], a[1]))
                         .max().getAsInt();

        int maxH = Arrays.stream(sizes)
                         .mapToInt(a -> Math.min(a[0], a[1]))
                         .max().getAsInt();

        return maxW * maxH;
    }
}