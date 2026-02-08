import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

        // 1랭킹이 비어있는 경우
        if (N == 0) {
            System.out.println(1);
            return;
        }

        // 랭킹이 꽉 찼고, 점수가 더 좋지 않으면 ㄴ
        if (N == P && score <= arr[N - 1]) {
            System.out.println(-1);
            return;
        }

        // 등수 계산
        int rank = 1;
        for (int i = 0; i < N; i++) {
            if (arr[i] > score) {
                rank++;
            } else {
                break;
            }
        }

        System.out.println(rank);
    }
}