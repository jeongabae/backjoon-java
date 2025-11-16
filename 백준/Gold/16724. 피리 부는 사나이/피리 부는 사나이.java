import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] v;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        v = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(v[i][j] == 0) {
                    dfs(i,j);
                }
            }
        }
        System.out.println(ans);
    }

    static void dfs(int x, int y) {
        v[x][y] = 1;

        int nx = x, ny = y;
        char d = map[x][y];

        if(d=='U') nx--;
        else if(d=='D') nx++;
        else if(d=='L') ny--;
        else if(d=='R') ny++;

        if(v[nx][ny]==0) {
            dfs(nx,ny);

            // 방문한 곳을 다시 방문하면 사이클...safe zone하나 생겨야댐
        }else if(v[nx][ny]==1) {
            ans++;
        }
        v[x][y] = 2;
    }
}