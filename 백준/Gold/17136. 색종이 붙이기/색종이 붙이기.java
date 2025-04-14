import java.io.*;
import java.util.*;

public class Main {

	static int[][] arr = new int[10][10];
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int minCnt = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
	}

	static void dfs(int r, int c, int cnt) {
		if (cnt >= minCnt)
			return;

		int nr = -1, nc = -1;
		boolean found = false;
		for (int i = r; i < 10 && !found; i++) {
			for (int j = (i == r ? c : 0); j < 10; j++) {
				if (arr[i][j] == 1) {
					nr = i;
					nc = j;
					found = true;
					break;
				}
			}
		}

		if (!found) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}

		for (int size = 5; size >= 1; size--) {
			if (paper[size] <= 0) continue;
			if (nr+size > 10 || nc+size > 10) continue;

			boolean canPlace = true;
			// 붙일 영역의 모든 칸이 1인지 확인
			for (int i = nr; i < nr + size && canPlace; i++) {
				for (int j = nc; j < nc + size; j++) {
					if (arr[i][j] != 1) {
						canPlace = false;
						break;
					}
				}
			}
			if (!canPlace)
				continue;

			// 색종이를 붙이기 -> 0으로
			for (int i = nr; i < nr + size; i++) {
				for (int j = nc; j < nc + size; j++) {
					arr[i][j] = 0;
				}
			}
			paper[size]--;

			dfs(nr, nc, cnt+1);

			for (int i = nr; i < nr + size; i++) {
				for (int j = nc; j < nc + size; j++) {
					arr[i][j] = 1;
				}
			}
			paper[size]++;
		}
	}
}