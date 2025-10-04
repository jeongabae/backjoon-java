import java.util.*;

class Solution {
    public int solution(String dirs) {
        int ans = 0;
        int x = 0, y = 0;
        Set<List<Integer>> v = new HashSet<>();
        
        Map<Character, int[]> dir = new HashMap<>();
        dir.put('U', new int[]{0,-1});
        dir.put('D', new int[]{0,1});
        dir.put('L', new int[]{-1,0});
        dir.put('R', new int[]{1,0});
        
        for(char d:dirs.toCharArray()){
            int nx = x+dir.get(d)[0];
            int ny = y+dir.get(d)[1];
            
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;

            List<Integer> path = Arrays.asList(x, y, nx, ny);
            List<Integer> reverse = Arrays.asList(nx, ny, x, y);

            if (!v.contains(path) && !v.contains(reverse)) {
                v.add(path);
                v.add(reverse);
                ans++;
            }

            x = nx;
            y = ny;
        }
        return ans;
    }
}