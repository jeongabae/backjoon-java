import java.util.*;

class Solution {
    
    static class Word {
        String word;
        int depth;

        Word(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) return 0;
        
        Deque<Word> q = new ArrayDeque<>();
        boolean[] v = new boolean[words.length];
        q.offer(new Word(begin, 0));

        while (!q.isEmpty()) {
            Word cur = q.poll();

            if (cur.word.equals(target)) {
                return cur.depth;
            }

            for (int i = 0; i < words.length; i++) {
                if (!v[i] && canTransform(cur.word, words[i])) {
                    q.offer(new Word(words[i], cur.depth + 1));
                    v[i] = true;
                }
            }
        }

        return 0;
    }

    boolean canTransform(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                  // 두 글자 이상 다르면 변환 불가
                if (diff > 1) return false;
            }
        }
        return diff == 1;
    }
    
}