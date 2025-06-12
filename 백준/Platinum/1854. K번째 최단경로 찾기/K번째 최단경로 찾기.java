import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static List<Node>[] graph;
	static PriorityQueue<Integer>[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); //정점 수
		M = Integer.parseInt(st.nextToken()); //간선 수
		K = Integer.parseInt(st.nextToken()); //K번째 최단 경로

		graph = new ArrayList[N+1];
		dist = new PriorityQueue[N+1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, c));
		}

		dijkstra(1);

		for (int i = 1; i <= N; i++) {
			if (dist[i].size() < K) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dist[i].peek()).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start].offer(0);

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node next : graph[cur.v]) {
				int newCost = cur.cost + next.cost;

				//K개 미만 -> 그냥 추가
				if (dist[next.v].size() < K) {
					dist[next.v].offer(newCost);
					pq.offer(new Node(next.v, newCost));
				}
				// K개면 -> 가장 큰 것보다 작을 때만 교체
				else if (dist[next.v].peek() > newCost) {
					dist[next.v].poll(); // 가장 큰 값 제거
					dist[next.v].offer(newCost);
					pq.offer(new Node(next.v, newCost));
				}
			}
		}
	}
	static class Node implements Comparable<Node> {
		int v;
		int cost;

		Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}