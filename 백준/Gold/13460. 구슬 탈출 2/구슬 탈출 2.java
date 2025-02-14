import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; 
    static int redR, redC, blueR, blueC;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    redR = i;
                    redC = j;
                } else if (board[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{redR, redC, blueR, blueC, 0});
        visited[redR][redC][blueR][blueC] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int rR = cur[0], rC = cur[1], bR = cur[2], bC = cur[3], cnt = cur[4];

            if (cnt >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                int[] redMove = move(rR, rC, dr[i], dc[i]);
                int[] blueMove = move(bR, bC, dr[i], dc[i]);

                if (board[blueMove[0]][blueMove[1]] == 'O') continue;
                if (board[redMove[0]][redMove[1]] == 'O') return cnt + 1;
                
                if (redMove[0] == blueMove[0] && redMove[1] == blueMove[1]) {
                    if (redMove[2] > blueMove[2]) {
                        redMove[0] -= dr[i];
                        redMove[1] -= dc[i];
                    } else {
                        blueMove[0] -= dr[i];
                        blueMove[1] -= dc[i];
                    }
                }

                if (!visited[redMove[0]][redMove[1]][blueMove[0]][blueMove[1]]) {
                    visited[redMove[0]][redMove[1]][blueMove[0]][blueMove[1]] = true;
                    q.offer(new int[]{redMove[0], redMove[1], blueMove[0], blueMove[1], cnt + 1});
                }
            }
        }
        return -1;
    }

    static int[] move(int r, int c, int dr, int dc) {
        int count = 0;
        while (board[r + dr][c + dc] != '#' && board[r][c] != 'O') {
            r += dr;
            c += dc;
            count++;
        }
        return new int[]{r, c, count};
    }
}