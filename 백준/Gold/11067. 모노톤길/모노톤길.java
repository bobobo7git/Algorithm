import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        int T = Integer.parseInt(br.readLine());
        while (T--> 0) {
            int N = Integer.parseInt(br.readLine());

            Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                pq.offer(new int[] {x, y});
            }
            int x = 0;
            int y = 0;
            int id = 1;
            int[][] cafes = new int[N+1][2];
            Deque<int[]> dq = new ArrayDeque<>();
            while (!pq.isEmpty()) {
                while (!pq.isEmpty() && pq.peek()[0] == x) {
                    dq.offer(pq.poll());
                }
                while (!dq.isEmpty() && dq.peek()[1] >= y) {
                    int[] p = dq.poll();
                    cafes[id++] = p;
                    y = p[1];
                }
                while (!dq.isEmpty() && dq.peek()[1] < y) {
                    int[] p = dq.pollLast();
                    cafes[id++] = p;
                    y = p[1];
                }
                if (!pq.isEmpty()) x = pq.peek()[0];
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int i=0; i<m; i++) {
                int k = Integer.parseInt(st.nextToken());
                bw.write(cafes[k][0]+" "+cafes[k][1]);
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
