import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int removeCnt = 0;

        for (char digit : number.toCharArray()) {
            // 앞자리 숫자가 작다면 제거한다 (큰 수를 만들기 위해)
            while (sb.length() > 0 && sb.charAt(sb.length()-1) < digit && removeCnt < k) {
                sb.deleteCharAt(sb.length()-1);
                removeCnt++;
            }
            sb.append(digit);
        }

        // 아직 제거해야 할 숫자가 남았다면, 뒤에서 자른다
        return sb.substring(0, sb.length()-(k-removeCnt));
    }
}