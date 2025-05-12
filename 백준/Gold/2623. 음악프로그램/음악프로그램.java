import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static int[] degree;
	static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

		degree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());

			for (int j = 1; j < cnt; j++) {
				int next = Integer.parseInt(st.nextToken());
				graph[prev].add(next);
				degree[next]++;
				prev = next;
			}
		}

		if (topoSort()) {
			for (int num : result) {
				System.out.println(num);
			}
		} else {
			System.out.println(0);
		}
	}

	static boolean topoSort() {
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) q.offer(i);
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);

			for (int next : graph[cur]) {
				degree[next]--;
				if (degree[next] == 0) {
					q.offer(next);
				}
			}
		}

		return result.size() == N;
	}
}