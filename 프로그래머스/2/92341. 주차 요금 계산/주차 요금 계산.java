import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0], baseFee = fees[1], unitTime = fees[2], unitFee = fees[3];

        Map<String, Integer> timeMap = new HashMap<>(); // 누적 시간(분)
        Map<String, Integer> inMap = new HashMap<>();   // 입차 시각

        for (String rec : records) {
            String[] parts = rec.split(" ");
            int t = toMin(parts[0]);
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                inMap.put(car, t);
            } else { // OUT
                int dur = t - inMap.remove(car);
                timeMap.put(car, timeMap.getOrDefault(car, 0) + dur);
            }
        }

        // 출차 안 한 차량 처리 (23:59 기준)
        int endOfDay = toMin("23:59");
        for (String car : inMap.keySet()) {
            int dur = endOfDay - inMap.get(car);
            timeMap.put(car, timeMap.getOrDefault(car, 0) + dur);
        }

        // 차량 번호 순 정렬 & 요금 계산
        List<String> cars = new ArrayList<>(timeMap.keySet());
        Collections.sort(cars);

        int[] ans = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            int time = timeMap.get(cars.get(i));
            ans[i] = calcFee(time, baseTime, baseFee, unitTime, unitFee);
        }
        return ans;
    }

    private int toMin(String hhmm) {
        String[] sp = hhmm.split(":");
        return Integer.parseInt(sp[0]) * 60 + Integer.parseInt(sp[1]);
    }

    private int calcFee(int time, int baseTime, int baseFee, int unitTime, int unitFee) {
        if (time <= baseTime) return baseFee;
        return baseFee + (int) Math.ceil((time - baseTime) / (double) unitTime) * unitFee;
    }
}
