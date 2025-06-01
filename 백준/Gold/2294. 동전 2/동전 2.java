import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10001; // 최대 k가 10000이므로..

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0; //0원을 만들기 위해 필요한 동전 수 : 0

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }

        System.out.println(dp[k]==INF ? -1 : dp[k]);
    }
}