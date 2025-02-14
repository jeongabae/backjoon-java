import java.io.*;
import java.util.*;

public class Main {

	static int l;
	static int[][] arr;
	static int[] dr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			l = Integer.parseInt(br.readLine());
			arr = new int[l][l];

			st = new StringTokenizer(br.readLine());
			int sX = Integer.parseInt(st.nextToken());
			int sY= Integer.parseInt(st.nextToken());
            
			st = new StringTokenizer(br.readLine());
			int eX = Integer.parseInt(st.nextToken());
			int eY= Integer.parseInt(st.nextToken());

			
			ArrayDeque<int[]> q = new ArrayDeque<>();
			q.offer(new int[]{sX, sY});
			arr[sX][sY]=1;
			
			while (!q.isEmpty()) {
				int[] now = q.poll();
				int nowX = now[0];
				int nowY = now[1];
				
				if (nowX == eX && nowY == eY)
					break;
				
				for (int i = 0; i < 8; i++) {
					int nr = nowX + dr[i];
					int nc = nowY + dc[i];
					
					if (nc < 0 || nc >= l || nr < 0 || nr >= l)
						continue;
					
					if (arr[nr][nc] == 0) {
						arr[nr][nc] = arr[nowX][nowY] + 1;
						q.offer(new int[] {nr, nc});
					}

				}
			}
			System.out.println(arr[eX][eY]-1);
		}

	}
}