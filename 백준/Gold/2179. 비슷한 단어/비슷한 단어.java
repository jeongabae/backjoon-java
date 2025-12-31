import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int maxLen = -1;
        int ansI = -1, ansJ = -1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int len = lcp(words[i], words[j]);

                if (len > maxLen) {
                    maxLen = len;
                    ansI = i;
                    ansJ = j;
                }
            }
        }

        System.out.println(words[ansI]);
        System.out.println(words[ansJ]);
    }

    static int lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) break;
            cnt++;
        }
        return cnt;
    }
}