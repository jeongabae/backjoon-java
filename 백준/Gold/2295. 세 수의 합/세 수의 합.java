import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);

        //x+y 모든 조합!!
        ArrayList<Integer> sum = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(sum);

        // 가장 큰 거부터...
        for (int k = N - 1; k >= 0; k--) {
            for (int z = k; z >= 0; z--) {
                int target = arr[k] - arr[z];

                if (Collections.binarySearch(sum, target) >= 0) {
                    System.out.println(arr[k]);
                    return;
                }
            }
        }
    }
}