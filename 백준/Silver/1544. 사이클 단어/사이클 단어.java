import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Set<String> hs = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            hs.add(minRotation(s));
        }

        System.out.println(hs.size());
    }
    static String minRotation(String s){
        int len = s.length();
        String ss = s+s;
        String min = ss.substring(0,len);
        
        for(int i=1;i<len;i++){
            String cur = ss.substring(i,i+len);
            if(cur.compareTo(min)<0) min = cur;
        }
        
        return min;
    }
}