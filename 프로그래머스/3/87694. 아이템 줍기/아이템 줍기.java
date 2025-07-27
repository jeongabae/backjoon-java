import java.util.*;

class Solution {
    static final int MAX = 102;
    static int[][] arr = new int[MAX][MAX];
    static int[][] v = new int[MAX][MAX];
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i = 0; i < MAX; i++) {
            Arrays.fill(arr[i], -1);  // 외부는 -1
            Arrays.fill(v[i], 1);     // 방문 전은 1
        }

        // 사각형 테두리는 1 내부는 0
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (x1 < i && i < x2 && y1 < j && j < y2) {
                        // 내부
                        arr[i][j] = 0;
                    } else if (arr[i][j] != 0) {
                        // 테두리
                        arr[i][j] = 1;
                    }
                }
            }
        }

        int cx = characterX * 2;
        int cy = characterY * 2;
        int ix = itemX * 2;
        int iy = itemY * 2;

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{cx, cy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (x == ix && y == iy) return v[x][y] / 2;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
                if (arr[nx][ny] == 1 && v[nx][ny] == 1) {
                    v[nx][ny] = v[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return 0;
    }
}