import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // min heap
        Queue<Integer> pq = new PriorityQueue<>();
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int a = Integer.parseInt(st.nextToken());
            pq.offer(a);
            sum += a;
            // 힙의 합을 P 이상으로 유지한다.
            while (!pq.isEmpty() && sum - pq.peek() >= P) {
                sum -= pq.poll();
            }

            int val = sum >= P ? pq.size() : -1;
            sb.append(val).append(' ');
        }
        System.out.print(sb.toString().trim());
    }
}
