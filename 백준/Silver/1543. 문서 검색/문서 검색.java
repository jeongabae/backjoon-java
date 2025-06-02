import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String word = br.readLine();
		
		int idx = 0;
		int cnt = 0;
		while(true) {
			int start = s.indexOf(word, idx);
			if(start<0) {
				break;
			}
			cnt++;
			idx=start+word.length();
		}
		System.out.println(cnt);
	}
	
}