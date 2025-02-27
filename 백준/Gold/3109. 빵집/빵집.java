import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] a;
	static boolean[][] v;
	static int maxCnt;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		a = new char[R][C];
		v = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				a[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			if (dfs(i, 0))
				maxCnt++;
		}

		System.out.println(maxCnt);
	}

	static boolean dfs(int x, int y) {
		v[x][y] = true;
		if (y == C - 1)
			return true;

		for (int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (0 <= nx && nx < R && 0 <= ny && ny < C && a[nx][ny] == '.') {
				if (!v[nx][ny]) {
					if (dfs(nx, ny))
						return true;
				}
			}
		}
		return false;
	}
}