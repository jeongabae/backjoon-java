import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int arr[][];
	static long dp[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j=0;j<N;j++) {
			arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new long[N][N];
		dp[0][0] = 1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int cnt = arr[i][j];
				if (cnt==0) continue;
				
				if(i+cnt<N) {
					dp[i+cnt][j]+=dp[i][j];
				}
				if(j+cnt<N) {
					dp[i][j+cnt]+=dp[i][j];
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
		
	}

}