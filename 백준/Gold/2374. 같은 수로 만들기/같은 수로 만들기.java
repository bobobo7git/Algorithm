import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        long cnt = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            // 현재 스택의 peek보다 높으면 다 꺼내서 add
            if (!stack.isEmpty() && n >= stack.peek()) {
                int min = Integer.MAX_VALUE;
                while (!stack.isEmpty() && n >= stack.peek()) {
                    min = Math.min(min, stack.pop());
                }
                cnt += n - min;
            }

            stack.push(n);
        }
        
        int max = 0;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            max = Math.max(max, pop);
            min = Math.min(min, pop);
        }
        cnt += max-min;

        System.out.println(cnt);
    }
}

