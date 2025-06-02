import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String s2 = br.readLine();
		
		int[] cntS = getAlphaCnt(s);
		int[] cntS2 = getAlphaCnt(s2);
		
		int ans=0;
		for (int i = 0; i < 26; i++) {
			ans+=Math.abs(cntS[i]-cntS2[i]);
		}
		System.out.println(ans);
	}
	
	static int[] getAlphaCnt(String s) {
		int[] cnt = new int[26];
		for (int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i)-'a']++;
		}
		return cnt;
	}
	
}