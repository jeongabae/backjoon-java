import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        while (q.size() > 1) {
            sb.append(q.poll()).append(" "); // 맨 위 카드 버림
            q.offer(q.poll()); // 그다음 카드를 맨 아래로
        }

        sb.append(q.poll()); // 마지막 카드
        System.out.println(sb);
    }
}