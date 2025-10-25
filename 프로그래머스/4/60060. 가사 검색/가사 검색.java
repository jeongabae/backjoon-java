import java.util.*;

class Solution {
    // 1. TrieNode 정의
    static class TrieNode {
        //다음 글자 노드 저장
        Map<Character, TrieNode> child = new HashMap<>();
        //이 노드 거치는 단어 수
        int cnt = 0;
    }
    // 2. Trie 구현
    static class Trie {
        TrieNode root = new TrieNode();

        //삽입
        void insert(String word) {
            //루트 기준으로 내려가면서 cnt++ 없으면 새 노드 생성
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                cur.cnt++;
                cur.child.putIfAbsent(c, new TrieNode());
                cur = cur.child.get(c);
            }
            cur.cnt++;
        }
        
       
        //검색
        int search(String query) {
            TrieNode cur = root;
            for (char c : query.toCharArray()) {
                if (c == '?') return cur.cnt;
                if (!cur.child.containsKey(c)) return 0;
                cur = cur.child.get(c);
            }
            return cur.cnt;
        }
        
        
    }

    public int[] solution(String[] words, String[] queries) {

        // 3. 길이별로 정방향/역방향 Trie 준비
        Map<Integer, Trie> trie = new HashMap<>();
        Map<Integer, Trie> revTrie = new HashMap<>();

        // 4. words 삽입
        for (String w : words) {
            int len = w.length();
            trie.putIfAbsent(len, new Trie());
            revTrie.putIfAbsent(len, new Trie());
            //길이 len짜리 단어들은 trie.get(len) 에 있는 트라이에 넣어라
            trie.get(len).insert(w);
            revTrie.get(len).insert(new StringBuilder(w).reverse().toString());
        }

        // 5. queries 탐색
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            int len = q.length();

            Trie t = (q.charAt(0) == '?') ? revTrie.get(len) : trie.get(len);
            //트라이 없으면 단어 없으니까..결과 0하고 다음으로
            if (t == null) { ans[i] = 0; continue; }

            //쿼리 뒤집고.
            if (q.charAt(0) == '?') q = new StringBuilder(q).reverse().toString();
            //'?'는 이제 검색할 필요가 없으니까 제거
            q = q.replaceAll("\\?", "");
            ans[i] = t.search(q);
        }

        // 6. 결과 반환
        return ans;
    }
}