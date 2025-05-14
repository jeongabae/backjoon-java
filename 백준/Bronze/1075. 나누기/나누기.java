import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int F = Integer.parseInt(br.readLine());

        int base = (N/100) * 100;

        for (int i = 0; i < 100; i++) {
            if ((base + i) % F == 0) {
                System.out.printf("%02d\n", i);
                break;
            }
        }
    }
}