import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    static boolean[] v;  // 티켓 사용 여부 (visited → v)

    public String[] solution(String[][] tickets) {
        v = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(list);  // 사전순 정렬
        return list.get(0).split(" ");  // 가장 빠른 경로 반환
    }

    static void dfs(int depth, String cur, String ans, String[][] tickets) {
        // 모든 티켓을 사용한 경우 → 경로 저장
        if (depth == tickets.length) {
            list.add(ans);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!v[i] && cur.equals(tickets[i][0])) {
                v[i] = true;
                dfs(depth + 1, tickets[i][1], ans + " " + tickets[i][1], tickets);
                v[i] = false;  // 백트래킹
            }
        }
    }
}
// import java.util.*;

// class Solution {
//     List<String> ans = new ArrayList<>();
//     Map<String, PriorityQueue<String>> graph = new HashMap<>();

//     public String[] solution(String[][] tickets) {
//         for (String[] ticket : tickets) {
//             String from = ticket[0];
//             String to = ticket[1];

//             graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
//         }

//         dfs("ICN");

//         return ans.toArray(new String[0]);
//     }

//     public void dfs(String from) {
//         PriorityQueue<String> pq = graph.get(from);

//         // 현재 공항에서 출발 가능한 목적지가 있을 때까지 탐색
//         while (pq != null && !pq.isEmpty()) {
//             String next = pq.poll();
//             dfs(next);
//         }

//         // 모든 경로를 다 돌아본 후에 현재 공항을 경로에 추가 0번 위치에 추가해야댐
//         ans.add(0, from);
//     }
// }