import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][][][] dp;
    static int[][] grid;
    static boolean[][] canGo;

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N+1][M+1];
        int er = -1, ec = -1;
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 2) {
                   er = i; ec = j;
                }
            }
        }

        dp = new int[N+1][M+1][K+1][3];

        for (int i=0; i<=N; i++) {
            for (int j=0; j<=M; j++) {
                for (int k=0; k<=K; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        canGo = new boolean[N+1][M+1];
        dfs(er, ec, 0, 0);
        int cnt = 0;
        canGo[er][ec] = false;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (canGo[i][j]) cnt++;
            }
        }
        System.out.println(cnt);
    }
    /*
    * 상태는 위에서 봤을 때 총 3가지
    * 0: 서있는 경우
    * 1: 세로로 누워있기 -> (r,c), (r+1,c)
    * 2: 가로로 누워있기 -> (r,c), (r,c+1)
    * */
    static int dfs(int r, int c, int move, int state) {
        if (move == K) {
            if (state == 0) canGo[r][c] = true;
            return 1;
        }
        if (dp[r][c][move][state] != -1) return dp[r][c][move][state];
        int ret = 0;

        // 서있을 때 -> 눕기
        if (state == 0) {
            // 상
            if (r-2 > 0 && grid[r-2][c] != 0 && grid[r-1][c] != 0) {
                ret += dfs(r-2, c, move+1, 1);
            }
            // 하
            if (r+2 <= N && grid[r+1][c] != 0 && grid[r+2][c] != 0) {
                ret += dfs(r+1, c, move+1, 1);
            }
            // 좌
            if (c-2 > 0 && grid[r][c-2] != 0 && grid[r][c-1] != 0) {
                ret += dfs(r, c-2, move+1, 2);
            }
            // 우
            if (c+2 <= M && grid[r][c+1] != 0 && grid[r][c+2] != 0) {
                ret += dfs(r, c+1, move+1, 2);
            }
        }
        // 세로 누워있을 때
        else if (state == 1) {
            // 일어나기
            // 상
            if (r-1 > 0 && grid[r-1][c] != 0) {
                ret += dfs(r-1, c, move+1, 0);
            }
            // 하
            if (r+2 <= N && grid[r+2][c] != 0) {
                ret += dfs(r+2, c, move+1, 0);
            }
            // 구르기
            // 좌
            if (c-1 > 0 && grid[r][c-1] != 0 && r+1 <= N && grid[r+1][c-1] != 0) {
                ret += dfs(r, c-1, move+1, 1);
            }
            // 우
            if (c+1 <= M && grid[r][c+1] != 0 && r+1 <= N && grid[r+1][c+1] != 0) {
                ret += dfs(r, c+1, move+1, 1);
            }
        }
        // 가로 누워있을 때
        else {
            // 일어나기
            // 좌
            if (c-1 > 0 && grid[r][c-1] != 0) {
                ret += dfs(r, c-1, move+1, 0);
            }
            // 우
            if (c+2 <= M && grid[r][c+2] != 0) {
                ret += dfs(r, c+2, move+1, 0);
            }
            // 구르기
            // 상
            if (r-1 > 0 && grid[r-1][c] != 0 && c+1 <= M && grid[r-1][c+1] != 0) {
                ret += dfs(r-1, c, move+1, 2);
            }
            // 하
            if (r+1 <= N && grid[r+1][c] != 0 && c+1 <= M && grid[r+1][c+1] != 0) {
                ret += dfs(r+1, c, move+1, 2);
            }
        }

        return dp[r][c][move][state] = ret;
    }

    static boolean oob(int r, int c) {
        return r <= 0 || c <= 0 || r > N || c > M;
    }

}