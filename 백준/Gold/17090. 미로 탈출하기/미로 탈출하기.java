import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] grid;
    static int[][] canGo;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new char[N][M];
        canGo = new int[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                dfs(i, j, i, j);
            }
        }
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (canGo[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }
    static int dfs(int r, int c, int sr, int sc) {
        if (canGo[r][c] > 0) return canGo[r][c];

        int dir = cmd2dir.get(grid[r][c]);
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
            return canGo[r][c] = 1;
        } else if (nr == sr && nc == sc) return canGo[r][c] = 2;
        else if (visited[nr][nc]) return canGo[r][c] = 2;

        visited[nr][nc] = true;
        canGo[r][c] = dfs(nr, nc, sr, sc);
        visited[nr][nc] = false;

        return canGo[r][c];

    }
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static final Map<Character, Integer> cmd2dir = Map.of('U', 0, 'R', 1, 'D', 2, 'L', 3);
}
