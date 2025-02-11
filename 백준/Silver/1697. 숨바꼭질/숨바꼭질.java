import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] arr = new int[100001];;
	static int[] visited = new int[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		visited[n] = 1;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (now == k)
				break;
			int[] next = { now - 1, now + 1, now * 2 };
			for (int i = 0; i < 3; i++) {
				if(!isRange(next[i]))
					continue;
				if (visited[next[i]] == 0) {
					visited[next[i]] = visited[now] + 1;
					q.add(next[i]);
				}
			}
		}
		System.out.println(visited[k]-1);
	}

	static boolean isRange(int x) {
		return x >= 0 && x <= 100000;
	}

}