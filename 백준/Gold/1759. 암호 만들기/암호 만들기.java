import java.io.*;
import java.util.*;

public class Main {
	    static int L, C;
	    static String[] arr;

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        L = Integer.parseInt(st.nextToken());
	        C = Integer.parseInt(st.nextToken());

	        arr = new String[C];
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < C; i++) {
	            arr[i] = st.nextToken();
	        }

	        Arrays.sort(arr);
	        comb(0, 0, new String[L]);
	    }

	    // 조합 생성 함수
	    static void comb(int start, int depth, String[] selected) {
	        if (depth == L) {
	            if (isValid(selected)) {
	                System.out.println(String.join("", selected));
	            }
	            return;
	        }

	        for (int i = start; i < C; i++) {
	            selected[depth] = arr[i];
	            comb(i + 1, depth + 1, selected);
	        }
	    }

	    // 모음1개 이상, 자음 1개 이상이어야 유효한 암호
	    static boolean isValid(String[] password) {
	        int mo = 0, ja = 0;
	        for (String s : password) {
	            if ("aeiou".contains(s)) mo++;
	            else ja++;
	        }
	        return mo >= 1 && ja >= 2;
	    }
	}