import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int digit = 15;
        int start = 0;
        for (int i=0; i<4; i++) {
            String line = br.readLine();
            for (int j=0; j<4; j++) {
                char c = line.charAt(j);
                if (c == 'L') start |= (1 << digit);
                digit--;
            }
        }
        br.readLine();

        digit = 15;
        int end = 0;
        for (int i=0; i<4; i++) {
            String line = br.readLine();
            for (int j=0; j<4; j++) {
                char c = line.charAt(j);
                if (c == 'L') end |= (1 << digit);
                digit--;
            }
        }
        System.out.println(bfs(start, end));

    }
    static int bfs(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[1<<16];
        q.offer(start);

        final int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, -1);
        dist[start] = 0;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == end) return dist[now];

            for (int i=0; i<16; i++) {
                // 좌우
                /*
                * 1110
                * 1110
                * 1110
                * 1110
                * */
                if (i % 4 != 0) {
                    int next = swap(now, i, i-1);
                    if (dist[next] == -1) {
                        dist[next] = dist[now]+1;
                        q.offer(next);

                    }
                }
                // 상하
                /*
                * 1111
                * 1111
                * 1111
                * 0000
                * */
                if (i > 3) {
                    int next = swap(now, i, i-4);
                    if (dist[next] == -1) {
                        dist[next] = dist[now]+1;
                        q.offer(next);

                    }
                }
            }
        }
        return dist[start];
    }

    static int swap(int val, int i, int j) {
        int bi = (val>>i) & 1;
        int bj = (val>>j) & 1;

        if (bi == bj) return val;
        return (val^(1<<i)^(1<<j));
    }

}
