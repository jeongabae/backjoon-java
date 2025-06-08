import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());//정점 개수
		int E = Integer.parseInt(st.nextToken());//간선 개수
		
		int K = Integer.parseInt(br.readLine()); //시작 정점 번호
		
		graph = new ArrayList[V+1];
		for (int i = 1; i <=V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); //출발
			int v = Integer.parseInt(st.nextToken()); //도착
			int w = Integer.parseInt(st.nextToken()); //가중치
			graph[u].add(new Node(v,w));
		}
		
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		dijkstra(K);
		
		for (int i=1; i<=V; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
		
		
    }
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//시작 정점은 비용 0, 거리 0
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			//가장 cost작은 노드 꺼냄
			Node cur = pq.poll();
			int now = cur.v;
			int cost = cur.cost;
			
			if(dist[now]<cost) continue;
			
			for(Node next:graph[now]) {
				int newCost = dist[now]+next.cost;
				if(newCost<dist[next.v]) {
					dist[next.v] = newCost;
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