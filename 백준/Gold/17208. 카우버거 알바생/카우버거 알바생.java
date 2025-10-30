
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] orders;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        orders = new int[N][2];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            orders[i] = new int[]{x, y};
        }

        dp = new int[N][M+1][K+1];
        for (int i=0; i<N; i++) {
            for (int j=0; j<=M; j++) {
                for (int k=0; k<=K; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        System.out.println(dfs(0, M, K));
    }
    static int dfs(int i, int m, int k) {
        // base case
        if (i == N) return 0;
        if (dp[i][m][k] != -1) return dp[i][m][k];

        int[] order = orders[i];
        int ret = 0;

        for (int j=i+1; j<=N; j++) {
            ret = Math.max(ret, dfs(j, m, k));
            if (m-order[0] >= 0 && k-order[1] >= 0) {
                ret = Math.max(ret, dfs(j, m-order[0], k-order[1])+1);
            }
        }
        

        return dp[i][m][k] = ret;
    }

}

