import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // 유저 ID별 최종 닉네임을 저장
        Map<String, String> hm = new HashMap<>();
        
        // 모든 기록을 돌면서 최종 닉네임 업데이트
        for (String rec : record) {
            String[] parts = rec.split(" ");
            String action = parts[0];
            
            if (action.equals("Enter") || action.equals("Change")) {
                String userId = parts[1];
                String nickname = parts[2];
                hm.put(userId, nickname);
            }
        }
        
        // Enter와 Leave만 메시지로 변환
        List<String> result = new ArrayList<>();
        
        for (String rec : record) {
            String[] parts = rec.split(" ");
            String action = parts[0];
            String userId = parts[1];
            
            if (action.equals("Enter")) {
                result.add(hm.get(userId) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                result.add(hm.get(userId) + "님이 나갔습니다.");
            }
        }
        
        return result.toArray(new String[0]);
    }
}