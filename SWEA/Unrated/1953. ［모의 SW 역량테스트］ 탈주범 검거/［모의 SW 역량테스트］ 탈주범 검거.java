import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] v;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] tunnel = {
		{},
		{ 0, 1, 2, 3 },
		{ 0, 1 },
		{ 2, 3 },
		{ 0, 3 },
		{ 1, 3 },
		{ 1, 2 },
		{ 0, 2 }
	};
	static int[] opposite = { 1, 0, 3, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			v = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = bfs();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { R, C, 1 });
		v[R][C] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];

			if (time >= L) continue;

			int type = map[x][y];
			for (int d : tunnel[type]) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (v[nx][ny]) continue;
				if (map[nx][ny] == 0) continue;

				int nextType = map[nx][ny];
				boolean connected = false;
				for (int nd : tunnel[nextType]) {
					if (opposite[d] == nd) {
						connected = true;
						break;
					}
				}

				if (connected) {
					v[nx][ny] = true;
					q.offer(new int[] { nx, ny, time + 1 });
					cnt++;
				}
			}
		}

		return cnt;
	}
}