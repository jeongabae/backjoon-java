class Solution {
    int count = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return count;
    }

    void dfs(int[] numbers, int target, int idx, int sum) {
        // 모든 숫자를 다 사용한 경우
        if (idx == numbers.length) {
            if (sum == target) count++;
            return;
        }

        // 현재 숫자를 더하거나 빼는 두 갈래 재귀
        dfs(numbers, target, idx + 1, sum + numbers[idx]);
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}