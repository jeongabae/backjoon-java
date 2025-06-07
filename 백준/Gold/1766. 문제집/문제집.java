import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] graph = new ArrayList[N+1];
		int[] degree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			//A->B 순서로 푸는게 좋음.
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			degree[b]++;
		}

		// 쉬운 문제부터 풀기위해 pq
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 진입차수가 0인 문제부터 시작
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				pq.offer(i);
			}
		}

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(" ");

			for (int next : graph[cur]) {
				degree[next]--;
				if (degree[next] == 0) {
					pq.offer(next);
				}
			}
		}

		System.out.println(sb);
	}
}