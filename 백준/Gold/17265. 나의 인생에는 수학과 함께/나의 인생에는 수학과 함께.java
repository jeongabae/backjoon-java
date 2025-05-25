import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] arr;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = input[j].charAt(0);
            }
        }

        dfs(0, 0, 0, arr[0][0]);
        System.out.println(max + " " + min);
    }

    static void dfs(int x, int y, int result, char operator) {
        if (x >= N || y >= N) return;

        char cur = arr[x][y];
        if ((x + y) % 2 == 0) {
            int num = cur - '0';
            if (x == 0 && y == 0) {
                result = num;
            } else {
                result = cal(result, operator, num);
            }

            if (x == N - 1 && y == N - 1) {
                max = Math.max(max, result);
                min = Math.min(min, result);
                return;
            }
        } else {
            operator = cur;
        }

        for (int dir = 0; dir < 2; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            dfs(nx, ny, result, operator);
        }
    }

    static int cal(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}