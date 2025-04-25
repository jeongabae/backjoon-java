import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!deque.isEmpty()) {
            // K-1번 앞에서 빼서 뒤로 다시 넣기
            for (int i = 0; i < K - 1; i++) {
                deque.offer(deque.poll());
            }
            // K번째 사람 제거
            sb.append(deque.poll());
            if (!deque.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}