import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] grid;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(binarySearch());

    }
    static int binarySearch() {
        int l = 0;
        int r = 1000000000;

        while (l <= r) {
            int mid = (l+r) / 2;
            if (bfs(mid)) {
                r = mid-1;
            } else l = mid+1;
        }
        return l;
    }
    static boolean bfs(int limit) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[N][N];
        q.offer(new int[]{0, 0});
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == N-1 && now[1] == N-1) return true;
            int h = grid[now[0]][now[1]];
            for (int i=0; i<4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (vis[nr][nc]) continue;
                if (Math.abs(h - grid[nr][nc]) > limit) continue;
                vis[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
        return false;
    }
}
