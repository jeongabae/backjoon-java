import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] arr;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0,-1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i=0;i<n;i++) {
			String line = br.readLine();
			for(int j=0;j<m;j++) {
				arr[i][j] = line.charAt(j)-'0';
			}
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		arr[0][0] = 1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			for(int d=0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<=nr&&nr<n&&0<=nc&&nc<m&&arr[nr][nc]==1) {
					arr[nr][nc] = arr[r][c]+1;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		System.out.println(arr[n-1][m-1]);
	}

}