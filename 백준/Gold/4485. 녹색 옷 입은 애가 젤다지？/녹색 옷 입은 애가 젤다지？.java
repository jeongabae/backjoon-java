import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] cave;
	static int[][] dist;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = 1;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;

			cave = new int[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;
				}
			}

			int ans = dijkstra();
			System.out.println("Problem " + t + ": " + ans);
			t++;
		}
	}

	private static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(0, 0, cave[0][0]));
		dist[0][0] = cave[0][0];
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.x, y = cur.y, cost = cur.cost;

			if (x == N - 1 && y == N - 1)
				return cost;

			if (dist[x][y] < cost)
				continue;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (0 <= nx && nx < N  && 0 <= ny && ny < N) {
					int newCost = cost + cave[nx][ny];
					if (newCost < dist[nx][ny]) {
						dist[nx][ny] = newCost;
						pq.offer(new Node(nx, ny, newCost));
					}
				}
			}
		}

		return -1;
	}

	static class Node implements Comparable<Node> {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}
}