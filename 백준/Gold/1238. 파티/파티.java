import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] graph, reverseGraph;
	static int[] fromX, toX;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//마을 개수
		int M = Integer.parseInt(st.nextToken());//도로 개수
		int X = Integer.parseInt(st.nextToken()); //X까지 제일 먼 곳 구해야함.
		
		graph = new ArrayList[N+1];
		reverseGraph = new ArrayList[N+1];
		for (int i = 1; i <=N; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); //출발
			int v = Integer.parseInt(st.nextToken()); //도착
			int T = Integer.parseInt(st.nextToken()); //소요시간
			graph[u].add(new Node(v,T));
			reverseGraph[v].add(new Node(u, T));
		}
		
		
		fromX = dijkstra(X, graph);

        toX = dijkstra(X, reverseGraph);
		
		int max = 0;
        for (int i = 1; i <= N; i++) {
            if (toX[i] != INF && fromX[i] != INF) {
                max = Math.max(max, toX[i] + fromX[i]);
            }
        }

        System.out.println(max);
		
		
    }
	
	static int[] dijkstra(int start, List<Node>[] g) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[g.length];
        Arrays.fill(dist, INF);
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int now = cur.v;
			int cost = cur.cost;
			
			if(dist[now]<cost) continue;
			
			for(Node next:g[now]) {
				int newCost = dist[now]+next.cost;
				if(newCost<dist[next.v]) {
					dist[next.v] = newCost;
					pq.offer(new Node(next.v, newCost));
				}
			}
		}
		return dist;
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