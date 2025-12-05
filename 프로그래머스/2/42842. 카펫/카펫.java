class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 노랑 격자의 세로길이를 하나씩 늘리면서 확인
        // 가로는 yellow / 세로
        // 나누어 떨어지지 않으면 패스
        int yh = 1;
        for (; yh<yellow; yh++) {
            if (yellow % yh != 0) continue;
            int yw = yellow / yh;
            // 갈색 격자 개수 계산
            int bcnt = yw * 2 + (yh+2) * 2;
            if (bcnt == brown) {
                break;
            }
        }
        // 가로 = yw+2
        // 세로 = yh+2
        answer[0] = yellow / yh + 2;
        answer[1] = yh+2;
        return answer;
    }
}