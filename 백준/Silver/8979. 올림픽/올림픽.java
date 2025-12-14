import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] medal = new int[N+1][3];
        
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int country = Integer.parseInt(st.nextToken());
            medal[country][0] = Integer.parseInt(st.nextToken()); // 금
            medal[country][1] = Integer.parseInt(st.nextToken()); // 은
            medal[country][2] = Integer.parseInt(st.nextToken()); // 동
        }

        int rank = 1;
        for (int i=1;i<=N;i++) {
            if (i == K) continue;

            if (isBetter(medal[i], medal[K])) rank++;
        }

        System.out.println(rank);
    }

    static boolean isBetter(int[] a, int[] b) {
        if (a[0] != b[0]) return a[0] > b[0];
        if (a[1] != b[1]) return a[1] > b[1];
        return a[2] > b[2];
    }
}