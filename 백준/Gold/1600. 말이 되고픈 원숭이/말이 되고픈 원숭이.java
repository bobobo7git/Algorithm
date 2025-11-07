import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        final int[] dr = {0, 1, 0, -1};
        final int[] dc = {1, 0, -1, 0};
        final int[] mr = {-2, -2, -1, -1, 1, 1, 2, 2};
        final int[] mc = {-1, 1, -2, 2, -2, 2, -1, 1};

        int[][][] visited = new int[N][M][K+1];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        // r, c, k
        q.offer(new int[]{0, 0, 0});
        visited[0][0][0] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            int k = now[2];
            if (r == N-1 && c == M-1) {
                break;
            }
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (grid[nr][nc] == 1 || visited[nr][nc][k] != -1) continue;
                visited[nr][nc][k] = visited[r][c][k] + 1;
                q.offer(new int[]{nr, nc, k});
            }
            if (k < K) {
                for (int i=0; i<8; i++) {
                    int nr = r + mr[i];
                    int nc = c + mc[i];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (grid[nr][nc] == 1 || visited[nr][nc][k+1] != -1) continue;
                    visited[nr][nc][k+1] = visited[r][c][k] + 1;
                    q.offer(new int[]{nr, nc, k+1});
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<=K; i++) {
            if (visited[N-1][M-1][i] == -1) continue;
            min = Math.min(min, visited[N-1][M-1][i]);
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
