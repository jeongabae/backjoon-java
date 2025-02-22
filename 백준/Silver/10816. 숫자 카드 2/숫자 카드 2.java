import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int x = Integer.parseInt(st.nextToken());
             int cnt = upperBound(arr,x) - lowerBound(arr,x);
             sb.append(cnt).append(" ");
        }
        System.out.println(sb);
	}
	
	static int lowerBound(int[] arr, int x) {
		int l=0, r=arr.length;
		while(l<r) {
			int m = l+(r-l)/2;
			if(arr[m]>=x) r=m;
			else l=m+1;
		}
		return l;
	}
	
	static int upperBound(int[] arr, int x) {
		int l=0, r=arr.length;
		while(l<r) {
			int m = l+(r-l)/2;
			if(arr[m]>x) r=m;
			else l=m+1;
		}
		return l;
	}
}