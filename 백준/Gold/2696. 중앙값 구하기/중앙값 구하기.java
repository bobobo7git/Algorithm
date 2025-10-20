import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        Queue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> right = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            left.clear();
            right.clear();
            int M = Integer.parseInt(br.readLine());
            sb.append((M+1)/2).append('\n');
            int cnt = 0;
            while (M > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i=1; i<=Math.min(10, M); i++) {
                    int n = Integer.parseInt(st.nextToken());
                    // 왼쪽 큐를 기준으로 삼는다
                    if (left.isEmpty()) left.offer(n);
                    else if (right.isEmpty()) {
                        if (left.peek() > n) {
                            right.offer(left.poll());
                            left.offer(n);
                        } else right.offer(n);
                    }
                    else if (right.peek() < n) {
                        right.offer(n);
                    } else left.offer(n);

                    if ((i & 1) == 1) {
                        while (right.size() > left.size()) left.offer(right.poll());
                        sb.append(left.peek()).append(' ');
                        cnt++;
                    } else {
                        while (left.size() > right.size()) right.offer(left.poll());
                        while (right.size() > left.size()) left.offer(right.poll());
                    }

                }
                if (cnt == 10) {
                    sb.append('\n');
                    cnt = 0;
                }
                M -= 10;
            }
            sb.append('\n');

        }
        System.out.println(sb);
    }
}

