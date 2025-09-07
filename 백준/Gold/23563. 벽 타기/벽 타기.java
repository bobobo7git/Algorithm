import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[][] grid = new char[H][W];

        Lucio lucio = null;
        Lucio tar = null;
        for (int i=0; i<H; i++) {
            String row = br.readLine();
            for (int j=0; j<W; j++) {
                char c = row.charAt(j);
                if (c == 'S') lucio = new Lucio(i, j, 0);
                if (c == 'E') tar = new Lucio(i, j, 0);
                grid[i][j] = c;
            }
        }
        // 벽에 인접한 칸 표시
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                if (grid[i][j] == '#') {
                    for (int k=0; k<4; k++) {
                        int ni = i + Lucio.dr[k];
                        int nj = j + Lucio.dc[k];
                        if (oob(ni, nj, H, W) || grid[ni][nj] == '#') continue;
                        grid[ni][nj] = '@';
                    }
                }
            }
        }
        
        System.out.println(bfs(grid, H, W, lucio, tar));
    }
    static int bfs(char[][] grid, int H, int W, Lucio lucio, Lucio target) {
        Deque<Lucio> dq = new ArrayDeque<>();
        int[][] dist = new int[H][W];
        final int INF = H*W+1;
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                dist[i][j] = INF;
            }
        }

        dist[lucio.r][lucio.c] = 0;
        dq.offer(lucio);

        while(!dq.isEmpty()) {
            Lucio now = dq.poll();
            if (now.r == target.r && now.c == target.c) return now.t;

            for (int i=0; i<4; i++) {
                int nr = now.r + Lucio.dr[i];
                int nc = now.c + Lucio.dc[i];

                if (oob(nr, nc, H, W) || grid[nr][nc] == '#') continue;
                // 벽 타고 있는 경우
                if (grid[now.r][now.c] == '@') {
                    // 벽에 인접한 칸으로 비용 0
                    if (grid[nr][nc] == '@' && dist[nr][nc] > now.t) {
                        dq.offerFirst(new Lucio(nr, nc, now.t));
                        dist[nr][nc] = now.t;
                    }
                    // 벽에 인접하지 않은 칸으로 비용 1
                    else {
                        if (dist[nr][nc] <= now.t+1) continue;
                        dist[nr][nc] = now.t+1;
                        dq.offer(new Lucio(nr, nc, now.t+1));
                    }
                }
                // 벽 안타고 있는 경우 비용 1
                else {
                    if (dist[nr][nc] <= now.t+1) continue;
                    dist[nr][nc] = now.t+1;
                    dq.offer(new Lucio(nr, nc, now.t+1));
                }

            }
        }
        return -1;
    }
    static boolean oob(int r, int c, int H, int W) {
        return r < 0 || c < 0 || r >= H || c >= W;
    }
    static class Lucio {
        int r, c;
        int t;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        public Lucio(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
}
