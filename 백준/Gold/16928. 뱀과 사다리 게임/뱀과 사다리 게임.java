import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ladders = new int[101];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladders[x] = y;
        }
        int[] snakes = new int[101];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            snakes[x] = y;
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[101];
        q.offer(1);
        dist[1] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == 100) break;

            for (int i=1; i<=6; i++) {
                int next = now + i;
                if (next > 100) continue;
                next = ladders[next] != 0 ? ladders[next] : next;
                next = snakes[next] != 0 ? snakes[next] : next;

                if (dist[next] != 0) continue;
                dist[next] = dist[now]+1;
                q.offer(next);
            }
        }

        System.out.println(dist[100]-1);
    }

}