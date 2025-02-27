import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] semester;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		degree = new int[N+1];
		semester = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			degree[b]++;
		}
		toSort();
		for (int i = 1; i <= N; i++) {
			sb.append(semester[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	static void toSort() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(degree[i]==0) {
				q.offer(i);
				semester[i] = 1;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next: graph[cur]) {
				degree[next]--;
				if(degree[next]==0) {
					q.offer(next);
					semester[next] = semester[cur]+1;
				}
			} 
		}
	}
}