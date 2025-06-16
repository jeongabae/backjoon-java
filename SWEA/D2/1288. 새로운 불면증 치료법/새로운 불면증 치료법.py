import java.io.*;
import java.util.*;

public class Solution {
	static int N, mul;
	static int num; // 비트마스크로 숫자 0~9 등장 여부 추적
	static final int FULL_MASK = (1<<10)-1; //0~9까지 모든 숫자 등장 1111111111

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			num = 0;
			mul = 1;

			while (true) {
				int cur = N*mul;
				int tmp = cur;

				while (tmp > 0) {
					int digit = tmp % 10;
					num |= (1<<digit); //해당 숫자 비트 켜기
					tmp /= 10;
				}

				// 1111111111이 된 경우
				if (num == FULL_MASK) {
					sb.append("#").append(tc).append(" ").append(cur).append("\n");
					break;
				}
				mul++;
			}
		}
		System.out.println(sb);
	}
}