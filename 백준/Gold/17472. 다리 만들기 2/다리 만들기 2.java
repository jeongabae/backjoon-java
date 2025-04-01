import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
        
    }

    static int N, M, islandCnt = 0;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<Edge> edgeList = new ArrayList<>();
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        
        int num = 2; //섬 번호 2부터 부여 (0,1은 초기 입력 때 썼으니까)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    labelIsland(i, j, num++);
                }
            }
        }
        islandCnt = num - 2; //2부터 시작했으니까.

        // 다리 만들기
        buildEdges();

        // 크루스칼
        parents = new int[islandCnt + 2];
        for (int i = 0; i < parents.length; i++) parents[i] = i;
        Collections.sort(edgeList);

        int result = 0, cnt = 0;
        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++cnt == islandCnt - 1) break;
            }
        }

        System.out.println(cnt == islandCnt - 1 ? result : -1);
    }

    static void labelIsland(int x, int y, int islandNum) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        v[x][y] = true;
        map[x][y] = islandNum;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                //범위 내, 방문 안했고, 육지(1)이면 방문처리하고 섬 번호 붙이기
                if (0 <= nx && nx < N && 0<=ny && ny < M &&
                        !v[nx][ny] && map[nx][ny] == 1) {
                    v[nx][ny] = true;
                    map[nx][ny] = islandNum;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static void buildEdges() {
    	//각 섬에서 가능한 모든 다리를 찾아 edgeList에 저장
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] >= 2) { // 섬이면
                    int from = map[x][y]; //출발 섬 번호
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        int len = 0;
                        
                        //한 방향으로 다리 놓기
                        while (0 <= nx && nx < N && 0<=ny && ny < M) {
                            if (map[nx][ny] == from) break; //출발 섬과 같으면 break
                            
                            if (map[nx][ny] == 0) { //바다면 길이 늘리고 직진
                                len++;
                                nx += dx[d];
                                ny += dy[d];
                                
                            } else { //평지(섬)에 도달했으면 간선 넣기
                                if (len >= 2) {
                                    int to = map[nx][ny];
                                    edgeList.add(new Edge(from, to, len));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }
}