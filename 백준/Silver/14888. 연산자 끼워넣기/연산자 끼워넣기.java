import java.io.*;
import java.util.*;

public class Main {
	static int N;
    static int[] nums;
    static int[] op = new int[4]; // +, -, *, /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(nums[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int result, int idx) {
        if (idx == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) { //해당 연산자 개수가 0이상이면
                op[i]--; //연산자 하나 사용
                int next = calc(result, nums[idx], i);
                dfs(next, idx + 1);
                op[i]++;
            }
        }
    }


    static int calc(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3: return a / b;
        }
        return 0;
    }
}