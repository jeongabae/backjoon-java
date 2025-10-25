import java.util.*;

class Solution {
    static class TrieNode{
        Map<Character, TrieNode> child = new HashMap<>();
        int cnt = 0;
    }
    
    static class Trie{
        TrieNode root = new TrieNode();
        
        void insert(String word){
            TrieNode cur = root;
            for(char c:word.toCharArray()){
                cur.cnt++;
                cur.child.putIfAbsent(c, new TrieNode());
                cur = cur.child.get(c);
            }
            cur.cnt++;
        }
        
        int cntTyping(String word){
            TrieNode cur = root;
            int cntT = 0;
            for(char c:word.toCharArray()){
                cur = cur.child.get(c);
                cntT++;
                if(cur.cnt==1) break;
            }
            return cntT;
        }
    }
    public int solution(String[] words) {
        Trie trie = new Trie();
        for(String w:words){
            trie.insert(w);
        }
        int ans = 0;
        for(String w:words){
            ans+=trie.cntTyping(w);
        }
        return ans;
    }
}