import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        FileToken[] list = new FileToken[files.length];

        for (int i = 0; i < files.length; i++) {
            list[i] = parse(files[i], i); // 파싱: HEAD/NUMBER만 필요
        }

        Arrays.sort(list, (a, b) -> {
            int h = a.head.compareTo(b.head); // 이미 대문자 저장
            if (h != 0) return h;
            int n = Integer.compare(a.number, b.number);
            if (n != 0) return n;
            return Integer.compare(a.idx, b.idx); // 안정성 보강(원래 자바는 안정 정렬)
        });

        String[] ans = new String[files.length];
        for (int i = 0; i < files.length; i++) ans[i] = list[i].original;
        return ans;
    }

    private FileToken parse(String s, int idx) {
        int n = s.length();
        int i = 0;

        // 1) HEAD: 첫 숫자 전까지
        while (i < n && !Character.isDigit(s.charAt(i))) i++;
        String head = s.substring(0, i);

        // 2) NUMBER: 최대 5자리 숫자
        int numStart = i, cnt = 0;
        while (i < n && Character.isDigit(s.charAt(i)) && cnt < 5) {
            i++;
            cnt++;
        }
        String numberStr = s.substring(numStart, i);
        int number = numberStr.isEmpty() ? 0 : Integer.parseInt(numberStr);

        return new FileToken(idx, head.toUpperCase(), number, s);
    }

    static class FileToken {
        final int idx;
        final String head;
        final int number;
        final String original;

        FileToken(int idx, String head, int number, String original) {
            this.idx = idx;
            this.head = head;
            this.number = number;
            this.original = original;
        }
    }
}
