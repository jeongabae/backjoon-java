import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 500001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈
        int K = Integer.parseInt(st.nextToken()); // 동생

        boolean[] even = new boolean[MAX]; // time%2==0
        boolean[] odd = new boolean[MAX];  // time%2==1

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        even[N] = true;

        int time = 0;

        while (true) {
            int target = K + time * (time + 1) / 2;
            if (target >= MAX) {
                System.out.println(-1);
                return;
            }

            if ((time % 2 == 0 && even[target]) || (time % 2 == 1 && odd[target])) {
                System.out.println(time);
                return;
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int now = q.poll();
                for (int next : new int[]{now - 1, now + 1, now * 2}) {
                    if (0 <= next && next < MAX) {
                        if (time % 2 == 0 && !odd[next]) {
                            odd[next] = true;
                            q.offer(next);
                        } else if (time % 2 == 1 && !even[next]) {
                            even[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }

            time++;
        }
    }
}