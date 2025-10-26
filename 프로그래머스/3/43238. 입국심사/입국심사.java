class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        
        // 최대 시간은?
        // 가장 느린사람이 전부 처리하는 경우
        int max = 0;
        for (int t: times) {
            max = Math.max(max, t);
        }
        long l = 1;
        long r = (long) max * n + 1;
        
        while (l <= r) {
            long mid = (l+r) / 2;
            long sum = 0;
            for (int t: times) {
                sum += mid / t;
            }
            // 합이 크면 더 줄여보기
            if (sum >= n) {
                r = mid-1;

            } else {
                l = mid+1;
                
            }
        }
        
        return l;
    }
}