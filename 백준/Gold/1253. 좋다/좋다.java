import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (isGood(i)) cnt++;
        }

        System.out.println(cnt);
    }

    static boolean isGood(int idx) {
        int target = arr[idx];
        int s = 0;
        int r = N-1;

        while (s < r) {
            if (s == idx) {
                s++;
                continue;
            }
            if (r == idx) {
                r--;
                continue;
            }

            int sum = arr[s] + arr[r];

            if (sum == target) return true;
            else if (sum < target) s++;
            else r--;
        }

        return false;
    }
}