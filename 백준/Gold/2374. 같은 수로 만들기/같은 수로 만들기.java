import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
        int highest = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            // 단조 감소스택이므로 항상 내림차순이 보장됨
            // peek은 가장 작은 수를 의미
            if (!stack.isEmpty() && stack.peek() <= n) {
                // 높이차만큼 메우기
                cnt += n - stack.pop();
            }
            stack.push(n);
            highest = Math.max(highest, n);
        }

        // 스택 peek은 가장 낮은 높이 저장
        // 최고 높이 - 최저점 더해주면 됨
        if (!stack.isEmpty()) cnt += highest - stack.pop();
        System.out.println(cnt);
    }
}

