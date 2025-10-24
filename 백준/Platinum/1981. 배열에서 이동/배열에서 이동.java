import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid;
    static int min, max;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        // 0~200 사이 정수
        // 최소는 시작에서 0
        // 최대의 최대는 최댓값-최솟값
        min = 222;
        max = -1;
        int l = 0, r = 0;
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }

        r = max-min;

        while (l <= r) {
            // 최대-최소 Key
            int mid = (l+r)/2;

            boolean canGo = false;
            // 닫힌구간 [s, e] 범위로만 이동가능한지 체크
            for (int s=min; s+mid<=max; s++) {
                int e = s+mid;
                // 이동가능하면 중단
                canGo = bfs(s, e);
                if (canGo) break;
            }

            if (canGo) {
                r = mid-1;
            } else {
                l = mid+1;
            }


        }
        System.out.println(l);
    }
    /*
    * vis를 N*N*V*V =100*100*200*200 = 400,000,000 -> TLE
    * [s, e] 구간의 값으로만 도달가능한지 검사
    * */
    static boolean bfs(int s, int e) {
        if (grid[0][0] < s || grid[0][0] > e) return false;

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        visited[0][0] = true;
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i=0; i<4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (visited[nr][nc] || grid[nr][nc] > e || grid[nr][nc] < s) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }

        return visited[N-1][N-1];
    }
}

