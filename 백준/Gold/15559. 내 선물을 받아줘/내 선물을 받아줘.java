import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static final int[] dr = {-1, 0, 0, 1};
    static final int[] dc = {0, -1, 1, 0};
    static final Map<Character, Integer> chr2key = Map.of(
            'N', 0,
            'W', 1,
            'E', 2,
            'S', 3
    );
    static boolean[][] visited;
    static boolean[][] isCycle;
    static int cycles = 0;
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
                grid[i][j] = chr2key.get(line.charAt(j));
            }
        }
        visited = new boolean[N][M];
        isCycle = new boolean[N][M];

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                visited[i][j] = true;
                dfs(i, j);
                visited[i][j] = false;
            }
        }
        System.out.println(cycles);
    }
    static boolean dfs(int r, int c) {
        if (isCycle[r][c]) return true;

        int nr = r + dr[grid[r][c]];
        int nc = c + dc[grid[r][c]];

        if (visited[nr][nc]) {
            cycles++;
            return isCycle[r][c] = true;
        }
        visited[nr][nc] = true;
        boolean nextCycle = dfs(nr, nc);
        visited[nr][nc] = false;

        if (nextCycle) return isCycle[r][c] = true;
        return isCycle[r][c];
    }

}
