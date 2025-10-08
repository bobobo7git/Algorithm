import java.util.*;
class Solution {
    public int solution(long n, int k, int[] enemy) {
        int answer = 0;
        // 라운드를 반복하면서 피를 계속 깎는다.
        // 현재까지 진행한 라운드를 최대힙에 삽입
        // 목숨이 부족하면 지금까지 진행한 라운드 중에서 가장 많이 강력한 라운드를
        // 우선순위큐에서 빼서 무적권을 사용한걸로 친다.
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        
        for (int e: enemy) {
            n -= e;
            pq.offer(e);
            // 방어 불가능하고, 무적권이 남아있을 때
            while (n < 0 && k > 0 && !pq.isEmpty()) {
                n += pq.poll();
                k--;
            }
            if (n < 0 && k == 0) break;
            answer++;
            
            
            
        }
        return answer;
    }
}