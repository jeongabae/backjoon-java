import java.util.*;
import java.util.regex.*;

class Solution {
    private static final Pattern P = Pattern.compile("^([^0-9]+)([0-9]{1,5})");

    public String[] solution(String[] files) {
        Arrays.sort(files, (s1, s2) -> {
            Matcher m1 = P.matcher(s1);
            Matcher m2 = P.matcher(s2);
            m1.find();
            m2.find();

            // HEAD 비교 (대소문자 무시)
            int headCmp = m1.group(1).compareToIgnoreCase(m2.group(1));
            if (headCmp != 0) return headCmp;

            // NUMBER 비교 (정수 비교)
            int n1 = Integer.parseInt(m1.group(2));
            int n2 = Integer.parseInt(m2.group(2));
            return Integer.compare(n1, n2);
        });

        return files;
    }
}