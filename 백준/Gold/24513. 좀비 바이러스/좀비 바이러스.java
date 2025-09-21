import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<Virus> q = new ArrayDeque<>();
        grid = new int[N][M];
        int[] cnt = new int[4];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1 || grid[i][j] == 2) {
                    q.offer(new Virus(i, j, grid[i][j]));
                    cnt[grid[i][j]]++;
                }
            }
        }

        while (!q.isEmpty()) {
            Virus now = q.poll();

            if (grid[now.r][now.c] == 3) continue;

            if (grid[now.r][now.c] == now.type * 11) {
                grid[now.r][now.c] = now.type;
                q.offer(now);
                cnt[now.type]++;
                continue;
            }
            for (int i=0; i<4; i++) {
                int nr = now.r + Virus.dr[i];
                int nc = now.c + Virus.dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                if (grid[nr][nc] + now.type * 11 == 33) {
                    grid[nr][nc] = 3;
                    cnt[3]++;
                    continue;
                }
                if (grid[nr][nc] == 0) {
                    grid[nr][nc] = now.type * 11;
                    q.offer(new Virus(nr, nc, now.type));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=3; i++) {
            sb.append(cnt[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
    static class Virus {
        int r, c;
        int type;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        Virus(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }
}
