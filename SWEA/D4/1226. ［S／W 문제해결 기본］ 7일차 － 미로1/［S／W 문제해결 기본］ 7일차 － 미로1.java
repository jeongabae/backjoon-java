import java.io.*;
import java.util.*;

public class Solution {
	    static int[][] map;
	    static boolean[][] v;
	    static int[] dx = {-1, 1, 0, 0};
	    static int[] dy = {0, 0, -1, 1};
	    static final int SIZE = 16;

	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuilder sb = new StringBuilder();
	        for (int t = 1; t <= 10; t++) {
	            int T = Integer.parseInt(br.readLine());
	            map = new int[SIZE][SIZE];
	            v = new boolean[SIZE][SIZE];
	            int sx = 0, sy = 0;

	            for (int i = 0; i < SIZE; i++) {
	                String line = br.readLine();
	                for (int j = 0; j < SIZE; j++) {
	                    map[i][j] = line.charAt(j) - '0';
	                    if (map[i][j] == 2) {
	                        sx = i;
	                        sy = j;
	                    }
	                }
	            }

	            int ans = bfs(sx, sy);
	            sb.append("#").append(t).append(" ").append(ans).append("\n");
	        }
	        System.out.println(sb);
	    }

	    static int bfs(int x, int y) {
	        Queue<int[]> q = new ArrayDeque<>();
	        v[x][y] = true;
	        q.offer(new int[]{x, y});

	        while (!q.isEmpty()) {
	            int[] cur = q.poll();
	            int cx = cur[0];
	            int cy = cur[1];

	            for (int d = 0; d < 4; d++) {
	                int nx = cx + dx[d];
	                int ny = cy + dy[d];

	                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && !v[nx][ny]) {
	                    if (map[nx][ny] == 0) {
	                        v[nx][ny] = true;
	                        q.offer(new int[]{nx, ny});
	                    } else if (map[nx][ny] == 3) {
	                        return 1; //도달 ㄱㄴ
	                    }
	                }
	            }
	        }
	        return 0; //도착지에 도달 불가
	    }
	}