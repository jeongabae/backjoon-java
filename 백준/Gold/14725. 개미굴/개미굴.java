import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Map<String, Node> child = new TreeMap<>();
    }

    static Node root = new Node();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Node cur = root;

            for (int j = 0; j < K; j++) {
                String food = st.nextToken();

                cur.child.putIfAbsent(food, new Node());
                cur = cur.child.get(food);
            }
        }

        print(root, 0);
    }

    static void print(Node node, int depth) {
        for (String key : node.child.keySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }

            System.out.println(key);
            print(node.child.get(key), depth + 1);
        }
    }
}