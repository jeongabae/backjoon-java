import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] arr;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		//보관 후 하루 -> 인접 토마토들이 익음.(상하좌우)(대각선은 아님)
		//며칠 지나면 다 익게되는지 최소 일수
		//일부 칸에는 토마토 들어있지 않을 수도
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		//0. M, N입력 받기
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		Queue<Point> q = new LinkedList<>();
		//1. 창고 입력 받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) {
					q.add(new Point(i,j));
					visited[i][j]=1;
				}
			}
		}
		
		//토마토이면 그 주변 토마토들 익게 하기 +1
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(int i=0;i<4;i++) {
				int nr = now.r+dr[i];
				int nc = now.c+dc[i];
				if(nr<0||nr>=N|nc<0||nc>=M) continue;
				if(visited[nr][nc]==0&&arr[nr][nc]==0) {
					q.add(new Point(nr, nc));
					visited[nr][nc] = visited[now.r][now.c]+1;
				}
			}
		}
		
		int max = 0;
        boolean all = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, visited[i][j]); //익은 날짜 중 최대를 찾아야..그게 모두 익을 때까지의 최소 날짜
                if (visited[i][j] == 0 && arr[i][j] == 0) { //방문하지 않았고, 0(안익어있는 토마토)가 있으면 다 안 익은거임.
                    all = false;
                    break;
                }
            }
        }
        if (all) System.out.println(max - 1); //최소 날짜. cf)저장할 때부터 익어있는 상태면 0인데(그 경우에도 1이 있어서 이 식 써도 됨.)
        else System.out.println(-1); //-1에 막혀 토마토가 못 익은 경우

	}

}

class Point{
	int r;
	int c;
	
	Point(int r, int c){
		this.r=r;
		this.c=c;
	}
}