import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().toUpperCase();
		
		int max = -1;
		char maxAlp = '?';
		for (char i='A'; i<='Z'; i++) {
			int cnt = getAlpCnt(s, i);
			if(cnt>max) {
				max = cnt;
				maxAlp = i;
			}else if(cnt==max) {
				maxAlp = '?';
			}
		}
		System.out.println(maxAlp);
	}
	static int getAlpCnt(String s, char c) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)==c) {
				cnt++;
			}
		}
		return cnt;
	}
}