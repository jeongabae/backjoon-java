import java.io.*;
import java.util.*;

public class Main {
    static int G, P; //게이트, 비행기 수
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G+1];
        //게이트가 자기자신을 부모로
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int ans = 0;

        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine()); // 비행기의 도킹 ㄱㄴ한 최대 게이트 번호
            int availableGate = find(gi);

            //도킹할 수 없으면 공항 폐쇄
            if (availableGate == 0) break;

            // 비행기를 해당 게이트에 도킹 ㄱ
            //도킹 되었으면 그 전 게이트로 루트 연결
            union(availableGate, availableGate - 1);
            ans++;
        }
        System.out.println(ans);
    }
}