import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {1,0};
        int[] dy = {0,1};
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        q.offer(new int[]{0,0});
        v[0][0] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];
            int jump = arr[cx][cy];
            if(cx == N-1 && cy == N-1){
                System.out.println("HaruHaru");
                return;
            }

            for(int d=0;d<2;d++){
                int nx = cx+dx[d]*jump;
                int ny = cy+dy[d]*jump;

                if(0<=nx && nx<N && 0<=ny && ny<N && !v[nx][ny]){
                    q.offer(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }
        }
        System.out.println("Hing");
    }
}