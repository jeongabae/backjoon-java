import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        long[] sharks = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sharks[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(sharks);

        Deque<Long> stack = new ArrayDeque<>();
        Queue<Long> q = new ArrayDeque<>();

        //작은 애들은 스택에 넣고 큰 애들은 큐에
        for (long size : sharks) {
            if (size < T) {
                stack.push(size);
            } else {
                q.offer(size);
            }
        }

        for (int i = 0; i < K; i++) {
            if (stack.isEmpty()) break;

            //스택에서 꺼내서 냠냠 먹어주고
            T += stack.pop();

            //크기 커졌으니까 큐에 있는 애들이랑 비교해서 잡아먹을 수 있으면 스택..먹이통에 넣기 ㅋ냠냠
            while (!q.isEmpty() && q.peek() < T) {
                stack.push(q.poll());
            }
        }

        System.out.println(T);
    }
}