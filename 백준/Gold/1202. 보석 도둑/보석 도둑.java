import java.io.*;
import java.util.*;

public class Main {
	static class Jewel implements Comparable<Jewel>{
		int m, v;
		
		Jewel(int m, int v){
			this.m=m;
			this.v=v;
		}

		@Override
		public int compareTo(Jewel o) {
			return this.m-o.m;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long ans = 0;
		
		int N = Integer.parseInt(st.nextToken());//보석 개수
		int K = Integer.parseInt(st.nextToken());//가방 개수
		
		Jewel[] jewels = new Jewel[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(m, v);
		}
		
		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		//보석은 무게 기준 오름차순 정렬
		//가방도 무게 기준 오름차순 정렬
		Arrays.sort(jewels);
		Arrays.sort(bags);
		
		//가장 비싼 보석 우선 선택 위해 최대힙..
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		int j = 0; //보석 인덱스
		for (int i = 0; i < K; i++) { //가방
			while(j<N&&jewels[j].m<=bags[i]) {
				//현재 가방에 담을 수 있는 보석의 가격을 최대 힙에 넣기
				pq.offer(jewels[j].v);
				j++;
			}
            
            //그 중 가장 비싼 보석을 꺼내어 담기
			if(!pq.isEmpty()) {
				ans+=pq.poll();
			}
		}
		System.out.println(ans);
    }
}