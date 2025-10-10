import java.io.*;
import java.util.*;

public class Main {
    static int N, M, C;
    static int[][] grid;
    static int[][][][] dp;
    static int[] di = {1, 0};
    static int[] dj = {0, 1};
    static final int MOD = 1_000_007;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new int[N+1][M+1];
        int idx = 1;
        for (int i=0; i<C; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            grid[r][c] = idx++;
        }

        /*
         * dp ijkl = (i, j)까지 왔을 때, k번 들르고 마지막 오락실이 l번일 때 경로 수
         * 0번 방문, 1번 방문, ... 순으로 반복
         * 어떤 점에서 이어가려면 지금까지 방문한 오락실 번호의 마지막 번호보다 커야함
         * 지금까지 방문한 오락실 번호를 반복하면서 다음에 이어줌
         * */
        // N, M, C <= 50 -> 50^4 = 6,250,000
        dp = new int[N+1][M+1][C+1][C+1];
        // 방문해도 경우의 수가 0일 수 있으므로 -1로 초기화
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=M; j++) {
                for (int k=0; k<=C; k++) {
                    for (int l=0; l<=C; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // 시작점부터 C~0번 방문하는 경우의 수 계산
        for (int k=0; k<=C; k++) {
            // 시작점이 오락실이라면 하나 깎고 들어감
            int res = grid[1][1] == 0 ?
                    dfs(1, 1, k, grid[1][1]) :
                    dfs(1, 1, k-1, grid[1][1]);
            sb.append(res).append(' ');
        }
        System.out.print(sb.toString().trim());

    }
    /*
    * 시작점부터 i,j까지 왔을 때 방문할 수 있는 횟수가 k번 남았고 마지막으로 방문한 오락실 번호가 l
    * */
    static int dfs(int i, int j, int k, int l) {
        if (k < 0) return 0;
        if (i == N && j == M && k == 0) return 1;
        if (dp[i][j][k][l] >= 0) return dp[i][j][k][l];

        int ret = 0;
        // 오른쪽, 아래쪽
        for (int d=0; d<2; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni > N || nj > M) continue;
            // 오락실이 아닌 경우
            if (grid[ni][nj] == 0) {
                ret = (ret + dfs(ni, nj, k, l)) % MOD;
            }
            // 오락실인 경우 현재까지 방문한 l보다 커야 함
            else if (grid[ni][nj] > l){
                ret = (ret + dfs(ni, nj, k-1, grid[ni][nj])) % MOD;
            }
        }

        return dp[i][j][k][l] = ret;
    }


}
