import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ans, maxNum;
	static int[][] arr;
	static boolean[][] v;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		v = new boolean[N][M];
		ans = 0;
		
		maxNum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxNum = Math.max(maxNum, arr[i][j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				v[i][j] = true;
				dfs(i,j,1,arr[i][j]);
				v[i][j] = false;
			}
		}

		System.out.println(ans);
	}

	static void dfs(int x, int y, int idx, int sum) {
		if(sum+maxNum*(4-idx)<=ans) return;
		if(idx==4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(0<=nx && nx<N && 0<=ny && ny<M && !v[nx][ny]) {
				v[nx][ny] = true;
				dfs(nx, ny, idx+1, sum+arr[nx][ny]);
				v[nx][ny] = false;
				
				if(idx==2) {
					v[nx][ny] = true;
					dfs(x, y, idx+1, sum+arr[nx][ny]);
					v[nx][ny] = false;
				}
			}
		}
	}
}