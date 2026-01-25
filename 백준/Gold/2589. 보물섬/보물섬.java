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
        grid = new int[N][M];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j) == 'L' ? 1 : 0;
            }
        }
        System.out.println(solve());
    }
    static int solve() {
        Queue<int[]> q = new ArrayDeque<>();

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                boolean[][] vis = new boolean[N][M];
                // 모든 육지에서 bfs
                if (grid[i][j] == 1 && !vis[i][j]) {
                    q.offer(new int[]{i, j, 0});
                    vis[i][j] = true;
                    int dist = 0;
                    while (!q.isEmpty()) {
                        int[] now = q.poll();
                        dist = Math.max(dist, now[2]);
                        for (int k=0; k<4; k++) {
                            int nr = now[0] + dr[k];
                            int nc = now[1] + dc[k];
                            if (oob(nr, nc) || vis[nr][nc]) continue;
                            vis[nr][nc] = true;
                            q.offer(new int[]{nr, nc, now[2]+1});
                        }
                    }
                    max = Math.max(max, dist);
                }
            }
        }
        return max;
    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M || grid[r][c] == 0;
    }

}
