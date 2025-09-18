import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        // 1 2 3 1 무조건 이 순서만 가능
        // 1 (1 2 3 1) 2 3 1 -> 2
        // stack
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> temp = new ArrayDeque<>();
        // 100만 * 4
        int[] burger = {1, 3, 2, 1};
        for (int i: ingredient) {
            stack.push(i);
            if (stack.size() >= 4) {
                int pos = 0;
                boolean found = true;
                temp.clear();
                while (pos < 4) {
                    int n = stack.pop();
                    temp.push(n);
                    if (burger[pos++] != n) {
                        found = false;
                        break;
                    }
                    
                }
                if (found) answer++;
                else while (!temp.isEmpty()) stack.push(temp.pop());
            }
            
        }
        return answer;
    }
}