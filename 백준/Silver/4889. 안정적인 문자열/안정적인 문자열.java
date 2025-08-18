
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        boolean isOver = false;
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (!isOver) {
            String input = br.readLine();
            Deque<Character> stack = new ArrayDeque<>();
            int cnt = 0;
            for (char c: input.toCharArray()) {
                if (c == '-') {
                    isOver = true;
                    break;
                }
                else if (c == '}') {
                    if (!stack.isEmpty() && stack.peek() == '{') stack.pop();
                    else {
                        cnt++;
                        stack.push('{');
                    }
                } else if (c == '{') stack.push(c);
            }
            cnt += stack.size() / 2;
            if (!isOver) sb.append(idx++).append(". ").append(cnt).append('\n');
        }
        System.out.print(sb);


    }

}
