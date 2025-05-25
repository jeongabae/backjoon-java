import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");

        Arrays.sort(nums, new Comparator<String>() {
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                // 내림차순
                return ba.compareTo(ab);
            }
        });

        // 맨 앞이 0 --> 전체가 0이라는 뜻~
        if (nums[0].equals("0")) {
            System.out.println("0");
        } else {
            for (String num : nums) {
                sb.append(num);
            }
            System.out.println(sb);
        }
    }
}