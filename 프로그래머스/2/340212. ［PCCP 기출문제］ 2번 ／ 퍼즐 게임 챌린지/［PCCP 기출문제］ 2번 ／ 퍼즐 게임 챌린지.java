class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        /*
        숙련도의 최솟값
        F F F ... F | T T T T
        -> 이진 탐색
        n = 300,000
        level 범위 = 닫힌구간 [1, 100000]
        제한 시간 내 해결가능한 경우만 주어짐
        -> 만렙이면 무조건 통과
        -> O(N*log100000)
        */
        int l = 1;
        int r = 100_000;
        while (l <= r) {
            int mid = (l+r) / 2;
            
            // mid로 시뮬레이션
            long time = 0L;
            for (int i=0; i<diffs.length; i++) {
                if (diffs[i] > mid) {
                    int wrongCnt = (diffs[i] - mid);
                    time += wrongCnt * times[i];
                    time += wrongCnt * times[i-1];
                    
                }
                time += times[i];
            }
            
            if (time > limit) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        
        return answer = l;
    }
}