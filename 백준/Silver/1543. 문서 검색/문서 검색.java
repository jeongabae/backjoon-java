import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String word = br.readLine();

        int cnt = 0;
        int idx = 0;

        while ((idx = s.indexOf(word, idx)) != -1) {
            cnt++;
            idx += word.length();
        }

        System.out.println(cnt);
    }
}