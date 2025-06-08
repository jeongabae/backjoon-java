import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] graph;
	static int[] dist, prev;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());//도시 개수
		int m = Integer.parseInt(br.readLine());//버스 개수
		
		
		graph = new ArrayList[n+1];
		dist = new int[n+1];
		prev = new int[n+1];
		for (int i = 1; i <=n; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); //출발
			int v = Integer.parseInt(st.nextToken()); //도착
			int cost = Integer.parseInt(st.nextToken()); //버스 비용
			graph[u].add(new Node(v,cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); //출발지
		int e = Integer.parseInt(st.nextToken()); //도착지
		
		dijkstra(s);
		
		//출력 1 최소비용
		System.out.println(dist[e]);
		//경로 역추척
		List<Integer> path = new ArrayList<>();
		for(int i=e;i!=0;i=prev[i]) {
			path.add(i);
		}
		Collections.reverse(path);
		//출력2 경로에 포함된 도시 수(출발, 도착 도시 포함)
		System.out.println(path.size());
		//출력3 경로 도시 번호
		for(int num:path) {
			System.out.print(num+" ");
		}
		
    }
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int now = cur.v;
			int cost = cur.cost;
			
			if(dist[now]<cost) continue;
			
			for(Node next:graph[now]) {
				int newCost = dist[now]+next.cost;
				if(newCost<dist[next.v]) {
					dist[next.v] = newCost;
					prev[next.v] = now;
					pq.offer(new Node(next.v, newCost));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int v, cost;
		
		Node(int v, int cost){
			this.v=v;
			this.cost=cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	
}