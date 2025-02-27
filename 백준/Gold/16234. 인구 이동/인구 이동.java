import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] arr;
	static boolean[][] v;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int days = 0;
		while (true) {
			v = new boolean[N][N];
			boolean moved = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j]) {
						if (bfs(i, j))
							moved = true;
					}
				}
			}

			if (!moved)
				break;
			days++;
		}

		System.out.println(days);
	}

	static boolean bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		List<int[]> union = new ArrayList<>(); //연합
		q.offer(new int[] { x, y });
		union.add(new int[] { x, y });
		v[x][y] = true;

		int sum = arr[x][y];

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (0<= nx && nx < N && 0 <= ny && ny < N && !v[nx][ny]) {
					int diff = Math.abs(arr[cx][cy] - arr[nx][ny]);
					if (L<=diff && diff <= R) {
						q.offer(new int[] { nx, ny });
						union.add(new int[] { nx, ny });
						v[nx][ny] = true;
						sum += arr[nx][ny];
					}
				}
			}
		}

		if (union.size() == 1)
			return false;

		int avg = sum / union.size();
		for (int[] pos : union) {
			arr[pos[0]][pos[1]] = avg;
		}

		return true;
	}
}