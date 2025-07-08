import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    static boolean[] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); //총 층수
        S = Integer.parseInt(st.nextToken()); //현재위치
        G = Integer.parseInt(st.nextToken()); //스타트링크(도착지)
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int[] arr = new int[F+1];
        arr[S] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        v = new boolean[F+1];
        q.offer(S);
        v[S] = true;

        while (!q.isEmpty()){
            int cur = q.poll();
            if(cur == G){
                System.out.println(arr[G]);
                return;
            }

            int[] can_next = {cur+U, cur-D};
            for(int d=0;d<2;d++){
                int next = can_next[d];

                if(!(1<=next&&next<F+1))
                    continue;
                if(!v[next]){
                    arr[next] = arr[cur]+1;
                    q.offer(next);
                    v[next] = true;
                }

            }
        }
        System.out.println("use the stairs");
    }
}