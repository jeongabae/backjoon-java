import java.io.*;
import java.util.*;

public class Main {
    static char[][] map = new char[5][5];
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Integer> selected = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        comb(0, 0);
        System.out.println(ans);
    }

    // 25칸 중 7개를 선택 조합
    static void comb(int idx, int cnt) {
        if (cnt == 7) {
            if (isValid()) ans++;
            return;
        }

        for (int i = idx; i < 25; i++) {
            selected.add(i);
            comb(i + 1, cnt + 1);
            selected.remove(selected.size() - 1);
        }
    }

    // 현재 선택된 7명이 연결되어 있고 'S'가 4명 이상인지 확인
    static boolean isValid() {
        boolean[] visited = new boolean[7];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(selected.get(0));
        visited[0] = true;
        int connectCnt = 1;
        int sCnt = 0;

        if (map[selected.get(0) / 5][selected.get(0) % 5] == 'S') sCnt++;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = cur / 5;
            int y = cur % 5;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int next = nx * 5 + ny;

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                    for (int i = 0; i < 7; i++) {
                        if (!visited[i] && selected.get(i) == next) {
                            visited[i] = true;
                            q.offer(next);
                            connectCnt++;
                            if (map[nx][ny] == 'S') sCnt++;
                        }
                    }
                }
            }
        }

        return connectCnt == 7 && sCnt >= 4;
    }
}