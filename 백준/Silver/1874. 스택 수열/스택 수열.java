import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        int p = 1;
        boolean can = true;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            // 현재 숫자가 스택 맨 위에 있다면 바로 Pop
            if (!stack.isEmpty() && stack.peek() == n) {
                stack.pop();
                sb.append("-\n");
            } else {
                while (stack.isEmpty() || stack.peek() < n && p <= N) {
                    stack.push(p++);
                    sb.append("+\n");
                }
                if (!stack.isEmpty() && stack.peek() == n) {
                    stack.pop();
                    sb.append("-\n");
                } else {
                    can = false;
                    break;
                }
            }
            if (p > N+1) {
                can = false;
                break;
            }
        }
        if (!can) System.out.println("NO");
        else System.out.print(sb);
    }

}