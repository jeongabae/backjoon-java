import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 연속 성공 시 보너스 조건
        int x = bandage[1]; // 초당 회복량
        int y = bandage[2]; // 추가 회복량

        int maxHealth = health;
        int time = 0;
        int successTime = 0;
        int attackIdx = 0;

        while (attackIdx < attacks.length) {
            int attackTime = attacks[attackIdx][0];
            int damage = attacks[attackIdx][1];

            // 공격 전까지 회복 처리
            while (time < attackTime) {
                time++;
                successTime++;

                // 회복 처리
                health += x;
                if (successTime == t) {
                    health += y;
                    successTime = 0;
                }

                // 최대 체력 넘지 않도록
                if (health > maxHealth) {
                    health = maxHealth;
                }
            }

            // 공격 받는 시간
            health -= damage;
            if (health <= 0) return -1;

            successTime = 0; // 연속 성공 초기화
            time++;
            attackIdx++;
        }

        return health;
    }
}
