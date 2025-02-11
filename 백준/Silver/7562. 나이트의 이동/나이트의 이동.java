import java.io.*;
import java.util.*;

public class Main {
	
	static int l;
	static int[][] arr;
	static int[][] visited;
	static int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//0. 테스트 케이스 개수
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
		//1. 체스판 한 변의 길이 l
		l = Integer.parseInt(br.readLine());
		
		//arr, visited
		arr = new int[l][l];
		visited = new int[l][l];
		
		//나이트가 현재 있는 칸
		st = new StringTokenizer(br.readLine());
		Point s = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
        //3. 나이트가 이동하려고 하는 칸 
		st = new StringTokenizer(br.readLine());
		Point e = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		Queue<Point> q = new LinkedList<>();
		q.add(s);
		visited[s.r][s.c] = 1;
		while(!q.isEmpty()) {
			Point now = q.poll();
			if (now.r == e.r && now.c == e.c) break; //찾음
			for(int i=0;i<8;i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (nc < 0 || nc >= l || nr < 0 || nr >= l) //범위 밖에 넘어간 경우 처리 x
					continue;
				if (visited[nr][nc] == 0) {  //방문하지 않은 경우만 +1
					visited[nr][nc] = visited[now.r][now.c] + 1;
					q.add(new Point(nr, nc));
			}
			
		}
		}
		System.out.println(visited[e.r][e.c]-1);
	}

}
}

class Point{
	int r;
	int c;
	Point(int r, int c) {
		this.r=r;
		this.c=c;
	}
}