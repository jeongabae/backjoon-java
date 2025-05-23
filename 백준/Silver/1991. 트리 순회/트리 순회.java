import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        char left, right;

        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree = new Node[26]; // A에서Z까쥐

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree[parent - 'A'] = new Node(left, right);
        }

        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');
    }

    static void preorder(char node) {
        if (node == '.') return;
        System.out.print(node);
        preorder(tree[node - 'A'].left);
        preorder(tree[node - 'A'].right);
    }

    static void inorder(char node) {
        if (node == '.') return;
        inorder(tree[node - 'A'].left);
        System.out.print(node);
        inorder(tree[node - 'A'].right);
    }

    static void postorder(char node) {
        if (node == '.') return;
        postorder(tree[node - 'A'].left);
        postorder(tree[node - 'A'].right);
        System.out.print(node);
    }
}