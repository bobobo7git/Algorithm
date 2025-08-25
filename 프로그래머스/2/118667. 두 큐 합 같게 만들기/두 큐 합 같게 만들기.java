import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long s1 = 0;
        long s2 = 0;
        for (int i: queue1) {
            q1.offer(i);
            s1 += i;
        }
        for (int i: queue2) {
            q2.offer(i);
            s2 += i;
        }
        int answer = 0;
        int N = queue1.length;
        while (s1 != s2 && !q1.isEmpty() && !q2.isEmpty() && answer <= 600000) {
            if (s1 < s2) {
                int n = q2.poll();
                s1 += n;
                s2 -= n;
                q1.offer(n);
            } else {
                int n = q1.poll();
                s2 += n;
                s1 -= n;
                q2.offer(n);
            }
            answer++;
        }
        // System.out.println(s1+" "+s2);
        
        return answer = s1 == s2 ? answer : -1;
    }
}