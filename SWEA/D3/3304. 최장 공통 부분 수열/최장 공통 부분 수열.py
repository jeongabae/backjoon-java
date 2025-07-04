import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            int aLen = a.length();
            int bLen = b.length();
            int[][] dp = new int[aLen+1][bLen+1];

            for (int i = 1; i <= aLen; i++) {
                for (int j = 1; j <= bLen; j++) {
                    if (a.charAt(i-1) == b.charAt(j - 1)) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(dp[aLen][bLen]).append("\n");
        }
        System.out.println(sb);
    }
}