import java.io.*;
import java.util.*;

public class Main {
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Meeting o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(s, e);
		}

		//시작시간으로 sort
		Arrays.sort(meetings);

		//종료시간 기준 최소힙
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.add(meetings[0].end);

		for (int i = 1; i < N; i++) {
			if (meetings[i].start >= pq.peek()) {
				//회의실 재사용
				pq.poll(); 
			}
			pq.add(meetings[i].end);
		}

		System.out.println(pq.size());
	}
}