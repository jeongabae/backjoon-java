import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String ans = "(None)";
        int bestPlay = -1;

        String target = normalize(m);

        for (String info : musicinfos) {
            String[] sp = info.split(",");
            String s = sp[0];
            String e = sp[1];
            String title = sp[2];
            String sheet = sp[3];

            int play = toMin(e) - toMin(s); // 재생 시간(분)
            String played = buildPlayed(normalize(sheet), play);

            // 포함되면 갱신: 재생 시간 긴 곡 우선, 같으면 입력 순서 우선(앞서서 이미 선택됨)
            if (played.contains(target)) {
                if (play > bestPlay) {
                    bestPlay = play;
                    ans = title;
                }
            }
        }
        return ans;
    }

    // "C# D# F# G# A#" 등을 단일 문자로 치환해서 겹침 문제 해결
    private String normalize(String str) {
        return str.replaceAll("C#", "c")
                  .replaceAll("D#", "d")
                  .replaceAll("F#", "f")
                  .replaceAll("G#", "g")
                  .replaceAll("A#", "a")
                .replaceAll("B#", "b")
                ;
    }

    // 재생 시간만큼 악보를 늘려서 실제 재생된 멜로디를 만든다
    private String buildPlayed(String sheet, int play) {
        StringBuilder sb = new StringBuilder(play);
        int n = sheet.length();
        for (int i = 0; i < play; i++) {
            sb.append(sheet.charAt(i % n));
        }
        return sb.toString();
    }

    // "HH:MM" -> 분
    private int toMin(String t) {
        String[] sp = t.split(":");
        int h = Integer.parseInt(sp[0]);
        int m = Integer.parseInt(sp[1]);
        return h * 60 + m;
    }
}
