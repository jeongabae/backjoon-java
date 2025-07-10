import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static HashSet<String> hs;
    static List<String> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            hs = new HashSet<>();
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                hs.add(br.readLine());
            }

            list = new ArrayList<>(hs);
            list.sort(Comparator
                    .comparingInt(String::length)
                    .thenComparing(Comparator.naturalOrder()));

            sb.append("#").append(tc).append("\n");
            for (String i:list) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }
}