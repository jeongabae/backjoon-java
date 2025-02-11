import java.io.*;
import java.util.*;

public class Main {
	static int w, h;
	static int[][] fire; //불 상태 기록
    static int[][] visited; //재환이 이동 기록
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
        	st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            fire = new int[h][w];
            visited = new int[h][w];
            Queue<Point> q = new LinkedList<>();
            Queue<Point> fireQ = new LinkedList<>();
            
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = line.charAt(j);
                    /*c가 뭔지에 따라서 다르게 처리*/
                    if (c == '#') { //벽인 경우 맵에 못 가게 -1처리, 방문 못 하게 -1처
                        fire[i][j] = visited[i][j] = -1;
                    } else if (c == '@') { //상근이 시작위치니까 큐에 넣어주고 방문 처리
                        q.add(new Point(i, j));
                        visited[i][j] = 1;
                    } else if (c == '*') { //불인 경우, fire큐에 넣어주고 fire맵에 1표시 
                        fireQ.add(new Point(i, j));
                        fire[i][j] = 1;
                    }
                }
            }
            
            /*불을 먼저 지르기 <- for 언제 불이 전파되는지 기록*/
            while (!fireQ.isEmpty()) { //fire큐가 빌 때까지
                Point now = fireQ.poll();
                for (int i = 0; i < 4; i++) { //상하좌우로 불 퍼뜨리기
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (isOutOfRange(nr, nc)) continue; //범위 나가면 continue
                    if (fire[nr][nc] == 0) { //아직 불이 안퍼진 곳이라면 퍼뜨리고 fireQ에 넣기
                        fire[nr][nc] = fire[now.r][now.c] + 1;
                        fireQ.add(new Point(nr, nc));
                    }
                }
            }

            /*재환이 이동*/
            boolean find = false;
            while (!q.isEmpty()) { //큐가 빌 때까지
                Point now = q.poll();
                if (isExit(now.r, now.c)) { //출구 찾았으면 출구까지 걸린 시간 출력, 찾았다 표시
                    System.out.println(visited[now.r][now.c]);
                    find = true;
                    break;
                }
                for (int i = 0; i < 4; i++) { //4방향 탐색
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (isOutOfRange(nr, nc)) continue; //범위 벗어나는 위치면 못 감
                    if (visited[nr][nc] != 0) continue; //이미 방문한 위치면 못 감
                    if (fire[nr][nc] == 0 || fire[nr][nc] > visited[now.r][now.c] + 1) { //불이 안 퍼졌고, 불이 퍼지기 전에 갈 수 있는 곳이면.
                        visited[nr][nc] = visited[now.r][now.c] + 1; //방문!
                        q.add(new Point(nr, nc)); //그리고 큐에 넣기
                    }
                }
            }
            if (!find) { //출구 못 찾았으면 임파시블 출력
                System.out.println("IMPOSSIBLE");
            }
        }

    }

    static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= h || c < 0 || c >= w;
    }

    static boolean isExit(int r, int c) {
        return r == 0 || r == h - 1 || c == 0 || c == w - 1;
    }
}

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}