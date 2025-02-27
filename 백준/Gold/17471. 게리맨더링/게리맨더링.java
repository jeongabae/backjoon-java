import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] people;
	static int[][] map;
	static boolean[] v;
	static boolean[] vv;
	static int minDiff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		people = new int[N];
		v = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int adjSize = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjSize; j++) {
				int e = Integer.parseInt(st.nextToken()) - 1;
				map[i][e] = map[e][i] = 1;
			}
		}
		minDiff = Integer.MAX_VALUE;
		sub(0);
		System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);

	}

	static void sub(int cnt) {
		if(cnt==N) {
			List<Integer> a = new ArrayList<>();
			List<Integer> b = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				if(v[i]) {
					a.add(i);
				}else {
					b.add(i);
				}
			}
			
			if(a.isEmpty()||b.isEmpty()) return;
			
			vv = new boolean[N];
			if(!bfs(a)||!bfs(b)) return;
			
			minDiff = Math.min(minDiff, calDiff(a,b));
			return;
		}
		
		v[cnt] = true;
		sub(cnt+1);
		v[cnt] = false;
		sub(cnt+1);
	}

	static boolean bfs(List<Integer> l) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(l.get(0));
		vv[l.get(0)] = true;
		int vCnt = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next:l) {
				if(!vv[next]&&map[cur][next]==1) {
					vv[next] = true;
					q.offer(next);
					vCnt++;
				}
			}
		}
		return vCnt == l.size();
	}

	static int calDiff(List<Integer> a, List<Integer> b) {
		int sumA =0, sumB = 0;
		for(int i : a) sumA+=people[i];
		for(int i : b) sumB+=people[i];
		return Math.abs(sumA-sumB);
	}
}