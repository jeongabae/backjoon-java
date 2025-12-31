import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> line = new ArrayList<>();

        for (int h = N; h >= 1; h--) {
            // 자기보다 큰 사람이 왼쪽에 k명
            int k = cnt[h-1];
            line.add(k, h);
        }

        for (int h : line) {
            sb.append(h).append(" ");
        }
        System.out.println(sb);
    }
}