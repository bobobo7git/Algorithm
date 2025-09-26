class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 0};
        // 투 포인터
        // 현재까지 부분수열의 합이 k보다 작거나 같다면 r++
        // 크면 l++
        int l = 0, r = 0;
        int sum = sequence[0];
        int min = sequence.length+1;
        while (l <= r && r < sequence.length) {
            if (sum == k && min > (r-l)) {
                answer[0] = l;
                answer[1] = r;
                min = r-l;
            }
            if (sum <= k && r < sequence.length-1) {
                sum += sequence[++r];
            } else {
                sum -= sequence[l++];
            }

            
            
        }

        
        return answer;
    }
}