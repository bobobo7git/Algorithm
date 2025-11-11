
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int SCALE = 2;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0 ,-1};
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N*SCALE+1][M*SCALE+1];

        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken())*SCALE;
            int sc = Integer.parseInt(st.nextToken())*SCALE;
            int er = Integer.parseInt(st.nextToken())*SCALE;
            int ec = Integer.parseInt(st.nextToken())*SCALE;
            // 모든 선은 변에 평행하게 그어짐
            for (int r=Math.min(er, sr); r<=Math.max(er, sr); r++) {
                grid[r][sc] = 1;
            }
            for (int c=Math.min(ec, sc); c<=Math.max(ec, sc); c++) {
                grid[sr][c] = 1;
            }
        }
//        for (int i=0; i<grid.length; i++) {
//            System.out.println(Arrays.toString(grid[i]));
//        }
        int color = 2;
        int min = N*M*SCALE;
        int max = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int res = bfs(i, j, color++);
                    min = Math.min(min, res);
                    max = Math.max(max, res);
                }
            }
        }
        System.out.println(max);
        System.out.println(min);
    }
    static int bfs(int r, int c, int i) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        int cnt = 0;
        grid[r][c] = i;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            // 모두 홀수인 칸만 실제 공간이고, 나머지는 좌표 경계
            if (now[0] % 2 == 1 && now[1] % 2 == 1) cnt++;
            for (int d=0; d<4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >=grid.length || nc >= grid[0].length) {
                    continue;
                }
                if (grid[nr][nc] != 0) continue;
                grid[nr][nc] = i;
                q.offer(new int[]{nr, nc});
            }
        }
        return cnt;
    }

}