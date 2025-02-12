import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] arr = new int[100001];;
	static int[] visited = new int[100001];
	static int[] count = new int[100001];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>(); //큐
        q.add(n);//큐에 수빈이 위치 넣고
        visited[n] = 1; //방문
        count[n] = 1; 
        
        while (!q.isEmpty()) { //큐가 비어있지 않으면 반복
            int now = q.poll(); //큐에서 하나 꺼냄

            if (now == k) break; //동생 만나면 멈춤
            int[] next = {now - 1, now + 1, now * 2}; //갈 수 있는거 세 가지
            for (int i = 0; i < 3; i++) { //세 가지 경우 해봄
                if(!isRange(next[i])) continue; //범위 벗어나면 continue
                if (visited[next[i]] == 0) { //방문하지 않았으면 방문
                    visited[next[i]] = visited[now] + 1;
                    count[next[i]] = count[now];
                    q.add(next[i]); //큐에 넣음
                } else if (visited[next[i]] == visited[now] + 1) {
                    count[next[i]] += count[now];
                }
            }
        }
        System.out.println(visited[k] - 1);
        System.out.println(count[k]);
    }
    static boolean isRange(int x) {
        return x >= 0 && x <= 100000;
    }

}