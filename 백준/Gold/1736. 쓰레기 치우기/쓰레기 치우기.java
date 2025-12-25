import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] rows;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rows = new ArrayList[N];
        for (int i = 0; i < N; i++) rows[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    rows[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (rows[i].isEmpty()) continue;

            ans++;
            int cur = rows[i].get(rows[i].size() - 1);

            for (int j = i + 1; j < N; j++) {
                if (rows[j].isEmpty()) continue;

                int last = rows[j].get(rows[j].size() - 1);
                if (last < cur) continue;

                while (!rows[j].isEmpty()
                        && rows[j].get(rows[j].size() - 1) >= cur) {
                    rows[j].remove(rows[j].size() - 1);
                }

                cur = last;
            }
        }

        System.out.println(ans);
    }
}