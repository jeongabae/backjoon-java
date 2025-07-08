import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] arr;
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> water = new ArrayDeque<>();
    static Queue<int[]> swan = new ArrayDeque<>();
    static Queue<int[]> nextSwan = new ArrayDeque<>();
    static int[] swanPos = new int[4]; // 두 백조의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        v = new boolean[R][C];

        int swanCnt = 0;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = line.charAt(j);

                if (arr[i][j] == '.' || arr[i][j] == 'L') {
                    water.offer(new int[]{i, j});

                    if (arr[i][j] == 'L') {
                        if (swanCnt == 0) {
                            swanPos[0] = i;
                            swanPos[1] = j;
                            swan.offer(new int[]{i, j});
                            v[i][j] = true;
                        } else {
                            swanPos[2] = i;
                            swanPos[3] = j;
                        }
                        swanCnt++;
                    }
                }
            }
        }

        int days = 0;

        while (true) {
            if (canMeet()) {
                System.out.println(days);
                break;
            }

            // 녹이기
            meltIce();

            // 다음 날 백조 탐색 준비
            swan = nextSwan;
            nextSwan = new ArrayDeque<>();
            days++;

        }
    }

    // 백조가 만날 수 있는지 확인
    static boolean canMeet() {
        while (!swan.isEmpty()) {
            int[] cur = swan.poll();
            int cx = cur[0];
            int cy = cur[1];

            //백조끼리 만났다~
            if (cx == swanPos[2] && cy == swanPos[3]) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx+dx[d];
                int ny = cy+dy[d];

                if (0<=nx && nx<R && 0<=ny && ny<C && !v[nx][ny]) {
                    v[nx][ny] = true;

                    if (arr[nx][ny] == 'X') {
                        // 빙하면 다음 날 탐색할 큐에 추가
                        nextSwan.offer(new int[]{nx, ny});
                    } else{
                        //물이면 계속 이동
                        swan.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        //아쉽지만 여기까지 오면 못만남
        return false;
    }

    // 얼음 녹이기
    static void meltIce() {
        int size = water.size();
        for (int i = 0; i < size; i++) {
            int[] cur = water.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx+dx[d];
                int ny = cy+dy[d];

                if (0<=nx && nx<R && 0<=ny && ny<C && !v[nx][ny]) {

                    if (arr[nx][ny] == 'X') {
                        arr[nx][ny] = '.';
                        water.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}