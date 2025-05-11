import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }

        dfs(0);
    }

    static void dfs(int depth) {
        if (solved) return; // 이미 해를 찾았으면 종료!!
        if (depth == blanks.size()) {
            printBoard();
            solved = true;
            return;
        }

        int[] pos = blanks.get(depth);
        int x = pos[0], y = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(x, y, num)) {
                board[x][y] = num;
                dfs(depth + 1);
                board[x][y] = 0; // 백트래킹
            }
        }
    }

    static boolean isValid(int x, int y, int num) {
        // 행 검사
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num) return false;
        }

        // 열 검사
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == num) return false;
        }

        // 3*3 박스 검사
        int boxX = (x / 3) * 3;
        int boxY = (y / 3) * 3;

        for (int i = boxX; i < boxX + 3; i++) {
            for (int j = boxY; j < boxY + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}