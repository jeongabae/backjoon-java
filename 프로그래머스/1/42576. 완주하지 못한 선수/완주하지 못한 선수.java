import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        //참가자 해시 맵에 넣기
        for(String p : participant){
            map.put(p, map.getOrDefault(p,0)+1);
        }
        //완주한 사람이면 -1
        for(String c : completion){
            map.put(c, map.get(c)-1);
        }
        //아직 값이 0이 아니면 완주하지 못했으니까 return
        for(String key : map.keySet()){
            if(map.get(key)!=0){
                return key;
            }
        }
        return "";
    }
}