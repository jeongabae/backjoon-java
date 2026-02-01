import java.util.*;

class Solution {
    static class Song {
        int idx;
        int play;

        Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }
    public int[] solution(String[] genres, int[] plays) {

        // 장르별 총 재생 수
        Map<String, Integer> totalMap = new HashMap<>();

        // 장르별 노래 목록
        Map<String, List<Song>> songMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            totalMap.put(genres[i],
                    totalMap.getOrDefault(genres[i], 0) + plays[i]);

            songMap.computeIfAbsent(genres[i], k -> new ArrayList<>())
                   .add(new Song(i, plays[i]));
        }

        // 장르를 총 재생 수 기준으로 정렬
        List<String> genreList = new ArrayList<>(totalMap.keySet());
        genreList.sort((a, b) -> totalMap.get(b) - totalMap.get(a));

        List<Integer> ans = new ArrayList<>();

        // 각 장르에서 최대 2곡 선택
        for (String genre : genreList) {
            List<Song> list = songMap.get(genre);

            list.sort((a, b) -> {
                if (b.play != a.play) return b.play - a.play;
                return a.idx - b.idx;
            });

            ans.add(list.get(0).idx);
            if (list.size() > 1) {
                ans.add(list.get(1).idx);
            }
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }
}