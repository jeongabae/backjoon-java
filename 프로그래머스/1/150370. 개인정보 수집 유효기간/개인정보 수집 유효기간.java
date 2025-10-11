import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ans = new ArrayList<>();
        
        // today를 총 일수로 변환
        int todayValue = toDays(today);
        
        // 약관별 유효기간 저장
        Map<String, Integer> termMap  = new HashMap<>();
        for (String t : terms) {
            String[] split = t.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]));
        }
        
        // 유효기간 확인, 파기
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            String date = split[0];
            String type = split[1];
            
            int dateValue = toDays(date);
            int month = termMap.get(type);
            
            // 수집일 + 유효기간(달*28일)
            int expired = dateValue + (month*28)-1;
            
            if (expired < todayValue) {
                ans.add(i+1);
            }
        }
        
        // 리스트를 배열로 변환해 리턴
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // 날짜 -> 일수로
    private int toDays(String date) {
        String[] arr = date.split("[.]");
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }
}