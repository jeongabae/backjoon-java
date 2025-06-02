import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if('A'<=c&&c<='Z') {
				sb.append((char)('a'+c-'A'));
			}else {
				sb.append((char)('A'+c-'a'));
			}
		}
		System.out.println(sb);
	}
}