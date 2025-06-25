import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int b: B) {
            pq.offer(b);
        }
        Arrays.sort(A);
    
        for (int a: A) {
            while (!pq.isEmpty() && pq.peek() <= a) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                answer++;
            }
        }
        return answer;
    }
}