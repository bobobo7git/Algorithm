import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // k개 수로 N을 만드는 경우의 수
        int[][] dp = new int[K+1][N+1];
        /*
        * N=20, K=2
        * // 13
        * 0 7 13
        * 1 6 13
        * 2 5 13
        * 3 4 13
        * 4 3 13
        * 5 2 13
        * 6 1 13
        * 7 0 13
        * -> 2개로 7을 만드는 경우의 수 + 13
        * => k-1개로 N-x를 만드는 경우의 수
        * */
        final int MOD = 1000000000;
        dp[0][0] = 1;
        for (int k=1; k<=K; k++) {
            for (int n=0; n<=N; n++) {
                for (int x=0; x<=n; x++) {
                    dp[k][n] = (dp[k][n] + dp[k-1][n-x]) % MOD;
                }
            }
        }
        System.out.println(dp[K][N] % MOD);

    }
}

