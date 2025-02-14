import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] arr = new int[100001];
	static int[] cnt = new int[100001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(n);
		arr[n] = 1;
		cnt[n] = 1;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (now == k)
				break;
			int[] can_next = { now - 1, now + 1, now * 2 };
			for (int i = 0; i < 3; i++) {
				int next = can_next[i];
				if(!(0<=next&&next<100001))
					continue;
				if (arr[next] == 0) {
					arr[next] = arr[now] + 1;
					q.offer(next);
					cnt[next] = cnt[now];
				}else if(arr[next]==arr[now]+1) {
					cnt[next] += cnt[now];
				}
			}
		}
		
		System.out.println(arr[k]-1);
		System.out.println(cnt[k]);
	}

}