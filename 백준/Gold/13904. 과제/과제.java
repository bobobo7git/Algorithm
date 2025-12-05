import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] works = new int[N][2];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            works[i] = new int[]{d, w};
        }
        Arrays.sort(works, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o2[1] - o1[1];
        });
        int p = N-1;
        int sum = 0;
        for (int day=works[N-1][0]; day>0; day--) {
            // 오늘 할 수 있으면 넣기
            while (p >= 0 && day <= works[p][0]) {
                pq.offer(works[p--]);
            }
            if (!pq.isEmpty()) {
                int[] done = pq.poll();
                sum += done[1];
            }
        }
        System.out.println(sum);
    }
}
