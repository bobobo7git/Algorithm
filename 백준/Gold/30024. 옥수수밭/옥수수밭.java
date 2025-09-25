import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];

        Queue<Corn> pq = new PriorityQueue<>((o1, o2) -> {
            return o2.g - o1.g;
        });
        boolean[][] visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || i == N-1 || j == 0 || j == M-1) {
                    pq.offer(new Corn(i, j, grid[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};

        int cnt = 0;
        while (!pq.isEmpty()) {
            Corn now = pq.poll();
            sb.append(now.r+1).append(' ').append(now.c+1).append('\n');
            if (++cnt == K) break;
            for (int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                pq.offer(new Corn(nr, nc, grid[nr][nc]));
            }
        }
        System.out.print(sb);

    }
    static class Corn {
        int r, c;
        int g;
        Corn(int r, int c, int g) {
            this.r = r;
            this.c = c;
            this.g = g;
        }
    }
}
