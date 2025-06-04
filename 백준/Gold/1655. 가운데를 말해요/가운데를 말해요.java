import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> small = new PriorityQueue<>((o1, o2) -> {
            return o1 < o2 ? 1 : -1;
        });
        
        PriorityQueue<Integer> big = new PriorityQueue<>();
        
        
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (small.size() == big.size()) {
            	small.offer(num);
            }else big.offer(num);

            if (!small.isEmpty() && !big.isEmpty()) {
                int s = small.peek();
                int b = big.peek();
                if (s > b) {
                    small.poll();
                    big.poll();
                    small.offer(b);
                    big.offer(s);
                }
            }
            sb.append(small.peek()).append('\n');
        }
        System.out.println(sb);
    }
}