import java.io.*;
import java.util.*;

public class Solution {
	static final int INF = 999999;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 사람 수
			int[][] dist = new int[N][N]; // 최단 거리

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (i == j) {
						dist[i][j] = 0;
					}else {
						dist[i][j] = (value==1) ? 1 : INF;
					}
				}
			}

			// 플로이드워셜 경출도
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}

			int minCC = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += dist[i][j];
				}
				minCC = Math.min(minCC, sum);
			}

			sb.append("#").append(t).append(" ").append(minCC).append("\n");
		}

		System.out.println(sb);
	}
}