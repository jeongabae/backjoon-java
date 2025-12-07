class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < food.length; i++) {
            int r = food[i] / 2;
            sb.append(String.valueOf(i).repeat(r));
        }

        String left = sb.toString();
        String right = sb.reverse().toString();

        return left + "0" + right;
    }
}
