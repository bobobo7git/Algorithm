import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        System.out.println(bfs(a, b, c, d));
    }
    static int bfs(int aCap, int bCap, int aTar, int bTar) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        int[][] dist = new int[aCap+1][bCap+1];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int a = now[0];
            int b = now[1];

            // terminate
            if (a == aTar && b == bTar) return dist[a][b];
            // fill
            if (a < aCap && (dist[aCap][b] == 0 || dist[aCap][b] > dist[a][b]+1)) {
                enq(q, aCap, b);
                dist[aCap][b] = dist[a][b]+1;
            }
            if (b < bCap && (dist[a][bCap] == 0 || dist[a][bCap] > dist[a][b]+1)) {
                enq(q, a, bCap);
                dist[a][bCap] = dist[a][b]+1;
            }
            // empty
            if (a > 0 && (dist[0][b] == 0 || dist[0][b] > dist[a][b]+1)) {
                enq(q, 0, b);
                dist[0][b] = dist[a][b]+1;
            }
            if (b > 0 && (dist[a][0] == 0 || dist[a][0] > dist[a][b]+1)) {
                enq(q, a, 0);
                dist[a][0] = dist[a][b]+1;
            }
            // move
            if (a > 0 && b < bCap) {
                if (a+b <= bCap && (dist[0][a+b] == 0 || dist[0][a+b] > dist[a][b]+1)) {
                    enq(q, 0, a+b);
                    dist[0][a+b] = dist[a][b]+1;
                } else {
                    int na = a - (bCap - b);
                    if (na >= 0 && (dist[na][bCap] == 0 || dist[na][bCap] > dist[a][b]+1)) {
                        enq(q, na, bCap);
                        dist[na][bCap] = dist[a][b]+1;
                    }
                }
            }
            if (b > 0 && a < aCap) {
                if (a+b <= aCap && (dist[a+b][0] == 0 || dist[a+b][0] > dist[a][b]+1)) {
                    enq(q, a+b, 0);
                    dist[a+b][0] = dist[a][b]+1;
                } else {
                    int nb = b - (aCap - a);
                    if (nb >= 0 && (dist[aCap][nb] == 0 || dist[aCap][nb] > dist[a][b]+1)) {
                        enq(q, aCap, nb);
                        dist[aCap][nb] = dist[a][b]+1;
                    }
                }
            }
        }
        return -1;

    }
    static void enq(Queue<int[]> q, int a, int b) {
        q.offer(new int[]{a, b});
    }

}
