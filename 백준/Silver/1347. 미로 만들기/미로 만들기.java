import java.io.*;

public class Main {
    static int N;
    static String cmd;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] v;
    static int minX, maxX, minY, maxY;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cmd = br.readLine();

        v = new boolean[101][101];

        int x = 50, y = 50;
        int dir = 0;

        v[x][y] = true;
        minX = maxX = x;
        minY = maxY = y;

        for (int i = 0; i < N; i++) {
            char c = cmd.charAt(i);

            if (c == 'F') {
                x += dx[dir];
                y += dy[dir];
                v[x][y] = true;

                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);

            } else if (c == 'L') {
                dir = (dir + 1) % 4;
            } else if (c == 'R') {
                dir = (dir + 3) % 4;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(v[i][j] ? '.' : '#');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}