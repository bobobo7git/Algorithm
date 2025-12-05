import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int n: people) dq.offer(n);
        
        while (!dq.isEmpty()) {
            if (dq.size() > 1 && dq.peek() + dq.peekLast() <= limit) {
                dq.poll();
                dq.pollLast();
                answer++;
            } else {
                dq.pollLast();
                answer++;
            }
        }
        return answer;
    }
}