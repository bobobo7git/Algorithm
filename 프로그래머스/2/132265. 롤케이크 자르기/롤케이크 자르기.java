import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int[] left = new int[10001];
        int[] right = new int[10001];
        int lu = 0;
        int ru = 0;
        for (int t: topping) {
            if (right[t]++ == 0) ru++;
        }
        for (int t: topping) {
            if (left[t]++ == 0) lu++;
            if (--right[t] == 0) ru--;
            if (lu == ru) answer++;
        }
        return answer;
    }
}